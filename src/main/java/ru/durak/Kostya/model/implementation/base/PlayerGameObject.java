package ru.durak.Kostya.model.implementation.base;

import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.PlayerSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.abstraction.game.Hand;

/**
 * Абстрактный класс, описывающий игрока.
 */
public abstract class PlayerGameObject extends GameObject implements PlayerSceneObject<CardSceneObject> {

    /**
     *  Ссылка на игру.
     */
    public final Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> game;

    /**
     * Хранилище карт.
     */
    protected final Hand<CardSceneObject> cards;

    /**
     * Инициализация объекта игрока.
     * @param cards Хранилище карт.
     * @param game Ссылка на игру.
     */
    public PlayerGameObject(Hand<CardSceneObject> cards, Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> game) {
        this.cards = cards;
        this.game = game;
    }

    /**
     * Метод добавления карты игроку.
     * @param newCard Добавляемая карта.
     */
    @Override
    public void add(CardSceneObject newCard) {
        if (newCard == null)
            return;

        order(cards.count());

        Vector indent = Resources.getMetrics().getHorizontalIndent();
        newCard.setLayer(-cards.count());
        newCard.setRotation(0);
        newCard.setPosition(Vector.sum(
                new Vector(-(Resources.getMetrics().getCardSize().getX() + indent.getX() * (cards.count())) / 2, 0),
                Vector.mult(indent, cards.count())));
        newCard.setParent(this);
        cards.add(newCard);
    }

    /**
     * Метод удаления карты у игрока.
     * @param card Удаляемая карта.
     */
    @Override
    public void remove(CardSceneObject card) {
        cards.remove(card);
        order(cards.count() - 1);
    }

    /**
     * Метод передачи хода игроку.
     */
    @Override
    public abstract void getMove();

    /**
     * Метод, возвращающий колличество карт у игрока.
     * @return Колличество карт у игрока.
     */
    @Override
    public int count() {
        return cards.count();
    }

    /**
     * Метод очистки эвентов карт.
     */
    @Override
    public void clearEvents() { }

    /**
     * Метод упорядочивания карт.
     * @param count Колличество карт.
     */
    protected void order(int count) {
        Vector indent = Resources.getMetrics().getHorizontalIndent();
        Vector start = new Vector(-(Resources.getMetrics().getCardSize().getX() + indent.getX() * (count)) / 2, 0);

        int index = 0;
        for (CardSceneObject card: cards) {
            card.setLayer(-index);
            card.setPosition(Vector.sum(start, Vector.mult(indent, index++)));
        }
    }
}
