package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.PlayerSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.abstraction.game.Hand;
import ru.durak.Kostya.model.implementation.base.PlayerGameObject;

/**
 * Класс, описывающий игрока,управляемого человаеком.
 */
public class ControlPlayerGameObject extends PlayerGameObject {

    /**
     * Инициализация объекта игрока.
     * @param cards Хранилище карт.
     * @param game Ссылка на игру.
     */
    public ControlPlayerGameObject(Hand<CardSceneObject> cards, Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> game) {
        super(cards, game);
    }

    /**
     * Переопределение метода добавления карты игроку.
     * @param newCard Добавляемая карта.
     */
    @Override
    public void add(CardSceneObject newCard) {
        super.add(newCard);
        PlayerSceneObject<CardSceneObject> player = this;
        newCard.setOnClick(mouseEvent -> {
            clearEvents();
            game.move(player, newCard);
        });
        sort();
        newCard.isHiddenFace(false);
    }

    /**
     * Переопределение метода передачи хода игроку.
     */
    @Override
    public void getMove() {
        Vector indent = new Vector(0, Resources.getMetrics().getHorizontalIndent().getX());
        for (CardSceneObject card: cards) {
            card.setOnMouseEnter(mouseEvent -> card.setPosition(Vector.diff(card.getPosition(), indent)));
            card.setOnMouseExit(mouseEvent -> card.setPosition(Vector.sum(card.getPosition(), indent)));
        }
    }

    /**
     * Переопределение метода очистки эвентов карт.
     */
    @Override
    public void clearEvents() {
        for (CardSceneObject card: cards) {
            card.setOnMouseEnter(mouseEvent -> {});
            card.setOnMouseExit(mouseEvent -> {});
        }
    }

    /**
     * Метод сортировки карт.
     */
    private void sort() {
        if (cards.count() == 0)
            return;

        Vector indent = Resources.getMetrics().getHorizontalIndent();

        Vector start = new Vector(-(Resources.getMetrics().getCardSize().getX() +
                indent.getX() * (cards.count() - 1)) / 2, 0);

        int index = 0;
        for (CardSceneObject card: cards) {
            card.setLayer(-index - 1);
            card.setPosition(Vector.sum(start, Vector.mult(indent, index++)));
        }
    }
}
