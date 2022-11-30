package ru.durak.Kostya.model.abstraction.game;

import ru.durak.Kostya.model.abstraction.game.enums.Suit;

/**
 * Карта.
 */
public interface Card {

    /**
     * Метод, возвращающий ранг.
     * @return Ранг карты.
     */
    int getRank();

    /**
     * Метод, возвращающий масть.
     * @return Масть карты.
     */
    Suit getSuit();

    /**
     * Метод изменения видимости карты.
     * @param value Значение видимости.
     */
    void isHiddenFace(boolean value);
}
