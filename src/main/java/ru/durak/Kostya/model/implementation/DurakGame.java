package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.infrastructure.Transform;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.*;
import ru.durak.Kostya.model.abstraction.builders.DeckBuilder;
import ru.durak.Kostya.model.abstraction.builders.PlayerBuilder;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.abstraction.game.enums.Suit;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс описывающий игру в дурака.
 */
public class DurakGame implements Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> {

    /**
     * Колличество карт у одного игрока по умолчанию.
     */
    private final int defaultCards;

    /**
     * Фабрика по созднию колоды карт.
     */
    private final DeckBuilder deckBuilder;

    /**
     * Фабрика по созданию игроков.
     */
    private final PlayerBuilder playersBuilder;

    /**
     * Объект сцены.
     */
    private final SceneObject scene;

    /**
     * Объект игрового стола.
     */
    private final TableSceneObject<CardSceneObject> table;

    /**
     * Колода карт.
     */
    private DeckSceneObject deck;

    /**
     * Список игроков.
     */
    private List<PlayerSceneObject<CardSceneObject>> players, activePlayers;

    /**
     * Активные игроки.
     */
    private PlayerSceneObject<CardSceneObject> current, currentAttack, currentDefend, firstSkip;

    /**
     * Кнопки.
     */
    private ButtonSceneObject startButton, giveButton, skipButton;

    /**
     * Козырь.
     */
    private Suit trump;

    /**
     * Игрок забирает карты.
     */
    private boolean isGive;

    /**
     * ИД стартующего игрока.
     */
    private int currentId;

    /**
     * Инициализация объекта игры.
     * @param scene Объект сцены.
     * @param deckBuilder Фабрика по созднию колоды карт.
     * @param playersBuilder Фабрика по созданию игроков.
     * @param table Объект игрового стола.
     */
    public DurakGame(SceneObject scene, DeckBuilder deckBuilder, PlayerBuilder playersBuilder, TableSceneObject<CardSceneObject> table) {
        this.defaultCards = Resources.getMetrics().getCardsCount();
        this.scene = scene;
        this.deckBuilder = deckBuilder;
        this.playersBuilder = playersBuilder;
        this.table = table;
    }

    /**
     * Метод, запускающий игру.
     */
    @Override
    public void start() {
        clear();
        createPlayers();
        createDeck();
        createButtons();
        deck.shuffle();
        startDistribute();
        setTrump();

        setCurrent(activePlayers.get(currentId));
        currentAttack = current;
        currentDefend = next(current);

        current.getMove();
    }

    /**
     * Метод хода игрока.
     * @param player Текущий игрок.
     * @param card Карта, которой ходит игрок.
     */
    @Override
    public void move(PlayerSceneObject<CardSceneObject> player, CardSceneObject card) {
        if (card == null || player != current)
            return;

        if (player == currentAttack &&
                (table.count() == 0 || table.first(tableCard -> tableCard.getRank() == card.getRank()) != null)) {

            player.remove(card);
            table.addDown(card);

            if (!isGive) {
                setCurrent(currentDefend);
            } else if (table.count() == defaultCards) {
                giveTableCards();
                if (!tryNewRound(next(currentDefend)))
                    return;
            } else if (current.count() == 0) {
                if (getPlayersWithCardsCount() == 0) {

                    giveTableCards();
                    if (!tryNewRound(next(currentDefend)))
                        return;

                } else {
                    currentAttack = next(currentDefend);
                    while (currentAttack.count() == 0)
                        currentAttack = next(currentAttack);

                    setCurrent(currentAttack);
                }
            }
        } else if (player == currentDefend) {

            CardSceneObject singleCard = table.getSingle();

            if ((card.getRank() > singleCard.getRank() && card.getSuit() == singleCard.getSuit()) ||
                (card.getSuit() == trump && singleCard.getSuit() != trump)) {

                player.remove(card);
                table.addUp(card);

                firstSkip = null;

                if (player.count() == 0 || table.count() == defaultCards || getPlayersWithCardsCount() == 0) {

                    table.clear();
                    if (!tryNewRound(currentDefend))
                        return;

                } else {
                    while (currentAttack.count() == 0 || currentAttack == currentDefend)
                        currentAttack = next(currentAttack);

                    setCurrent(currentAttack);
                }
            }
        }
        current.getMove();
    }

    /**
     * Игрок забирает карты.
     * @param player Текущий игрок.
     */
    @Override
    public void give(PlayerSceneObject<CardSceneObject> player) {
        if (player != current || player != currentDefend)
            return;

        if (table.count() == defaultCards || getPlayersWithCardsCount() == 0) {

            giveTableCards();
            if (!tryNewRound(next(currentDefend)))
                return;

        } else {

            isGive = true;
            firstSkip = null;
            while (currentAttack.count() == 0 && currentAttack != currentDefend)
                currentAttack = next(currentAttack);

            setCurrent(currentAttack);
        }

        current.getMove();
    }

    /**
     * Бито.
     * @param player Текущий игрок.
     */
    @Override
    public void skip(PlayerSceneObject<CardSceneObject> player) {
        if (player != current || player != currentAttack || table.count() == 0)
            return;

        if (firstSkip == null)
            firstSkip = player;

        while (true) {
            currentAttack = next(currentAttack);
            if (currentAttack == currentDefend)
                continue;

            if (currentAttack == firstSkip) {
                if (isGive)
                    giveTableCards();
                else
                    table.clear();

                if (!tryNewRound(isGive ? next(currentDefend) : currentDefend))
                    return;

                break;
            } else if (currentAttack.count() > 0) {
                setCurrent(currentAttack);
                break;
            }
        }

        current.getMove();
    }

    /**
     * Метод, определяющий, ходит ли сейчас атакующий игрок.
     * @return Ходит ли сейчас атакующий игрок.
     */
    @Override
    public boolean isAttack() {
        return current == currentAttack;
    }

    /**
     * Метод, возвращающий колличество текущих карт в игре.
     * @return Колличество текущих карт в игре.
     */
    @Override
    public int getCount() {
        return table.count();
    }

    /**
     * Метод, возвращающий все текущие карты игры.
     * @return Все текущие карты игры.
     */
    @Override
    public Collection<CardSceneObject> getAll() {
        return table.getAll();
    }

    /**
     * Метод, возвращающий текущую неотбитую карту.
     * @return Текущая неотбитая карта.
     */
    @Override
    public CardSceneObject getSingle() {
        return table.getSingle();
    }

    /**
     * Метод, возвращающий козырь.
     * @return Козырь.
     */
    @Override
    public Suit getTrump() {
        return trump;
    }

    /**
     * Метод, возврщающий колличество игроков с картами.
     * @return Колличество игроков с картами.
     */
    protected int getPlayersWithCardsCount() {
        int count = 0;
        for (PlayerSceneObject<CardSceneObject> player: activePlayers)
            if (player.count() > 0 && player != currentDefend)
                count++;

        return count;
    }

    /**
     * Метод, передающий игроку карты со стола.
     */
    protected void giveTableCards() {
        Collection<CardSceneObject> cards = table.getAll();
        for (CardSceneObject card: cards)
            currentDefend.add(card);

        table.removeAll();
    }

    /**
     * Попытка начала нового кона.
     * @param nextAttackPlayer Следующий игрок.
     * @return Возвращает false, если игра закончена.
     */
    protected boolean tryNewRound(PlayerSceneObject<CardSceneObject> nextAttackPlayer) {
        distribute(previous(currentDefend));
        int count = 0;
        PlayerSceneObject<CardSceneObject> possibleLoser = null;
        for (PlayerSceneObject<CardSceneObject> player: activePlayers)
            if (player.count() > 0) {
                possibleLoser = player;
                count++;
            }

        if (count == 0) {
            System.out.println("Ничья!");
            currentId = 0;
            current = null;
            return false;
        } else if (count == 1) {
            int index = players.indexOf(possibleLoser);
            System.out.println("Проиграл игрок №" + (index + 1));
            currentId = index == 0 ? players.size() - 1 : index - 1;
            current = null;
            return false;
        }

        currentAttack = nextAttackPlayer;
        while (currentAttack.count() == 0)
            currentAttack = next(currentAttack);

        activePlayers.removeIf(currentPlayer -> currentPlayer.count() == 0);
        currentDefend = next(currentAttack);
        setCurrent(currentAttack);

        isGive = false;
        firstSkip = null;

        return true;
    }

    /**
     * Метод создания кнопок.
     */
    protected void createButtons() {
        startButton = new ButtonGameObject("Заново!");
        startButton.setPosition(new Vector(180, 290));
        startButton.setParent(scene);
        startButton.setOnClick(mouseEvent -> start());

        giveButton = new ButtonGameObject("Беру!");
        giveButton.setPosition(new Vector(180, 320));
        giveButton.setParent(scene);

        skipButton = new ButtonGameObject("Бито!");
        skipButton.setPosition(new Vector(180, 350));
        skipButton.setParent(scene);
    }

    /**
     * Метод очистки игры.
     */
    protected void clear() {
        if (players == null)
            return;

        for (PlayerSceneObject<CardSceneObject> player: players)
            player.setParent(null);

        table.clear();
        deck.setParent(null);
        startButton.setParent(null);
        giveButton.setParent(null);
        skipButton.setParent(null);
    }

    /**
     * Метод зоздания игроков.
     */
    protected void createPlayers() {
        Transform[] transform = Resources.getMetrics().getPlayersTransform();
        List<PlayerSceneObject<CardSceneObject>> tempPlayers = playersBuilder.build(this);
        int playersCount = Math.min(transform.length, tempPlayers.size());
        players = new ArrayList<>(playersCount);
        for (int i = 0; i < playersCount; i++) {
            players.add(tempPlayers.get(i));
            players.get(i).setPosition(transform[i].getPosition());
            players.get(i).setRotation(transform[i].getRotation());
            players.get(i).setParent(scene);
        }
        activePlayers = new ArrayList<>(players);
    }

    /**
     * Метод создания колоды.
     */
    protected void createDeck() {
        deck = deckBuilder.build();
        deck.setParent(scene);
        Vector sceneSize = Resources.getMetrics().getSceneSize();
        Vector cardSize = Resources.getMetrics().getCardSize();
        double verticalIndent = cardSize.getY() - cardSize.getX() + Resources.getMetrics().getVerticalIndent().getX();
        deck.setPosition(new Vector(
                sceneSize.getX() / 2 - cardSize.getX() / 2 - Resources.getMetrics().getHorizontalIndent().getX(),
                verticalIndent * 2));
        deck.setRotation(90);
    }

    /**
     * Метод раздачи карт на старте игры.
     */
    protected void startDistribute() {
        for (int i = 0; i < defaultCards; i++)
            for (PlayerSceneObject<CardSceneObject> player : activePlayers) {
                if (deck.count() == 0)
                    return;

                if (player.count() == defaultCards)
                    continue;

                player.add(deck.pop());
            }
    }

    /**
     * Метод добавления карт игрокам.
     * @param startPlayer Первый игрок.
     */
    protected void distribute(PlayerSceneObject<CardSceneObject> startPlayer) {
        PlayerSceneObject<CardSceneObject> player = startPlayer;
        do {
            for (int i = 0; i < defaultCards; i++) {
                if (deck.count() == 0)
                    return;

                if (player.count() >= defaultCards)
                    continue;

                player.add(deck.pop());
            }
            player = next(player);

        } while (player != startPlayer);
    }

    /**
     * Метод изменения текущего игрока.
     * @param player Текущий игрок.
     */
    protected void setCurrent(PlayerSceneObject<CardSceneObject> player) {
        current = player;
        giveButton.setOnClick(mouseEvent -> {
            player.clearEvents();
            give(player);
        });
        skipButton.setOnClick(mouseEvent -> {
            player.clearEvents();
            skip(player);
        });
    }

    /**
     * Метод определения козыря.
     */
    protected void setTrump() {
        CardSceneObject card = deck.peekLast();
        trump = card.getSuit();
        card.isHiddenFace(false);
        card.setPosition(Vector.sum(card.getPosition(), Resources.getMetrics().getHorizontalIndent()));
        card.setRotation(90);
    }

    /**
     * Метод, возвращающий следующего игрока.
     * @param player Текущий игрок.
     * @return Следующий игрок.
     */
    protected PlayerSceneObject<CardSceneObject> next(PlayerSceneObject<CardSceneObject> player) {
        int i = activePlayers.indexOf(player);
        return i == activePlayers.size() - 1 ? activePlayers.get(0) : activePlayers.get(++i);
    }

    /**
     * Метод, возвращающий предыдущего игрока.
     * @param player Текущий игрок.
     * @return Предыдущий игрок.
     */
    protected PlayerSceneObject<CardSceneObject> previous(PlayerSceneObject<CardSceneObject> player) {
        int i = activePlayers.indexOf(player);
        return i == 0 ? activePlayers.get(activePlayers.size() - 1) : activePlayers.get(--i);
    }
}
