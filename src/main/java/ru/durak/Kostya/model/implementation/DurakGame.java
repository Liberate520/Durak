package ru.durak.Kostya.model.implementation;

import javafx.scene.control.Button;
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
import java.util.List;

public class DurakGame implements Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> {

    private final int defaultCards;

    private final DeckBuilder deckBuilder;

    private final PlayerBuilder playersBuilder;

    private final SceneObject scene;

    private final TableSceneObject<CardSceneObject> table;

    private DeckSceneObject deck;

    private List<PlayerSceneObject<CardSceneObject>> players;

    private List<PlayerSceneObject<CardSceneObject>> activePlayers;

    private PlayerSceneObject<CardSceneObject> current;

    private PlayerSceneObject<CardSceneObject> currentDefend;

    private ButtonSceneObject giveButton;

    private ButtonSceneObject skipButton;

    private int currentId;

    private Suit trump;

    public DurakGame(SceneObject scene, DeckBuilder deckBuilder, PlayerBuilder playersBuilder, TableSceneObject<CardSceneObject> table) {
        this.defaultCards = Resources.getMetrics().getCardsCount();
        this.scene = scene;
        this.deckBuilder = deckBuilder;
        this.playersBuilder = playersBuilder;
        this.table = table;
        currentId = 0;
    }

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
        currentDefend = next(current);

        current.getMove();
    }

    public void move(PlayerSceneObject<CardSceneObject> player, CardSceneObject card) {
        if (player != current || card == null)
            return;

        if (table.count() == 0 || (!table.hasSingle() &&
                table.first(tableCard -> card.getRank() == tableCard.getRank()) != null)) {

            current.remove(card);
            table.add(card);
            setCurrent(currentDefend);

        } else if (table.hasSingle() && (
                (card.getRank() > table.peek().getRank() && card.getSuit() == table.peek().getSuit()) ||
                (card.getSuit() == trump && table.peek().getSuit() != trump))) {

            current.remove(card);
            table.add(card);

            if (current.count() == 0 || table.count() == defaultCards) {
                table.clear();

                if (!isContinue()) {
                    finish();
                    return;
                }


                setCurrent(currentDefend);
                currentDefend = next(current);
            } else
                setCurrent(previous(currentDefend));
        }

        current.getMove();
    }

    public void give(PlayerSceneObject<CardSceneObject> player) {
        if (player != current || player != currentDefend || !table.hasSingle())
            return;

        for (CardSceneObject card: table.getAll()) {
            table.remove(card);
            current.add(card);
        }

        if (!isContinue()) {
            finish();
            return;
        }
        setCurrent(next(current));
        currentDefend = next(current);
        current.getMove();
    }

    public void skip(PlayerSceneObject<CardSceneObject> player) {
        if (player != current || player == currentDefend || table.hasSingle())
            return;

        if (next(current) == previous(currentDefend)) {
            if (!isContinue()) {
                finish();
                return;
            }
            table.clear();
            setCurrent(currentDefend);
            currentDefend = next(current);
        } else {
            PlayerSceneObject<CardSceneObject> next = next(current);
            if (next == currentDefend)
                next = next(currentDefend);

            setCurrent(next);
        }

        current.getMove();
    }

    protected boolean isContinue() {
        distribute(previous(current));

        activePlayers.removeIf(player -> player.count() == 0);
        return activePlayers.size() >= 2;
    }

    public Suit getTrump() {
        return trump;
    }

    protected void createButtons() {
        giveButton = new ButtonGameObject("Беру!");
        giveButton.setPosition(new Vector(180, 290));
        giveButton.setParent(scene);

        skipButton = new ButtonGameObject("Бито!");
        skipButton.setPosition(new Vector(180, 320));
        skipButton.setParent(scene);
    }

    protected void clear() {
        if (players != null)
            for (PlayerSceneObject<CardSceneObject> player: players)
                player.setParent(null);

        if (table != null)
            table.clear();

        if (deck != null)
            deck.setParent(null);

        if (giveButton != null)
            giveButton.setParent(null);

        if (skipButton != null)
            skipButton.setParent(null);
    }

    protected void setCurrent(PlayerSceneObject<CardSceneObject> player) {
        current = player;

        System.out.println(players.indexOf(current));

        giveButton.setOnClick(mouseEvent -> {
            if (current.getActiveButton())
                this.give(current);
        });

        skipButton.setOnClick(mouseEvent -> {
            if (current.getActiveButton())
                this.skip(current);
        });
    }

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

    protected void createDeck() {
        deck = deckBuilder.build();
        deck.setParent(scene);
        Vector sceneSize = Resources.getMetrics().getSceneSize();
        Vector cardSize = Resources.getMetrics().getCardSize();
        double verticalIndent = cardSize.getY() - cardSize.getX() + Resources.getMetrics().getVerticalIndent().getX();
        deck.setPosition(new Vector(sceneSize.getX() / 2 - cardSize.getX() / 2, verticalIndent));
        deck.setRotation(90);
    }

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

    protected void setTrump() {
        if (trump != null)
            return;

        CardSceneObject card = deck.peekLast();
        trump = card.getSuit();
        card.isHiddenFace(false);
        //card.setPosition(Vector.diff(card.getPosition(), Resources.getMetrics().getHorizontalIndent()));
        card.setRotation(90);
    }

    protected void finish() {
        int count = activePlayers.size();
        int loserIndex = count == 0 ? 0 : players.indexOf(activePlayers.get(0));
        currentId = loserIndex == 0 ? players.size() - 1 : loserIndex - 1;
        current = null;
        currentDefend = null;
        System.out.println(count == 0 ? "Ничья" : ("Проиграл игрок #" + loserIndex));
    }

    protected PlayerSceneObject<CardSceneObject> next(PlayerSceneObject<CardSceneObject> player) {
        int i = activePlayers.indexOf(player);
        return i == activePlayers.size() - 1 ? activePlayers.get(0) : activePlayers.get(++i);
    }

    protected PlayerSceneObject<CardSceneObject> previous(PlayerSceneObject<CardSceneObject> player) {
        int i = activePlayers.indexOf(player);
        return i == 0 ? activePlayers.get(activePlayers.size() - 1) : activePlayers.get(--i);
    }
}
