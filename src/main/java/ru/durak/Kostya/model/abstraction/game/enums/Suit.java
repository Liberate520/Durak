package ru.durak.Kostya.model.abstraction.game.enums;

/**
 * Масть.
 */
public enum Suit {
    /**
     * Червы
     */
    hearts(0),

    /**
     * Буби
     */
    tiles(1),

    /**
     * Пики
     */
    clovers(2),

    /**
     * Крести
     */
    pikes(3);

    /**
     * Инициализация масти.
     * @param value Числовое значение масти.
     */
    Suit(int value) { }
}
