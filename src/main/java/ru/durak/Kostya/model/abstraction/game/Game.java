package ru.durak.Kostya.model.abstraction.game;

import ru.durak.Kostya.model.abstraction.game.enums.Suit;

import java.util.Collection;

/**
 * Игра.
 * @param <T> Тип элемента игры.
 * @param <TPlayer> Тип игрока.
 */
public interface Game<T, TPlayer extends Player<T>> {

    /**
     * Метод, запускающий игру.
     */
    void start();

    /**
     * Метод хода игрока.
     * @param player Текущий игрок.
     * @param value Элемент, которым ходит игрок.
     */
    void move(TPlayer player, T value);

    /**
     * Метод, передающий текущие элнинты игры игроку.
     * @param player Текущий игрок.
     */
    void give(TPlayer player);

    /**
     * Метод пропуска хода.
     * @param player Текущий игрок.
     */
    void skip(TPlayer player);

    /**
     * Метод, определяющий, ходит ли сейчас атакующий игрок.
     * @return Ходит ли сейчас атакующий игрок.
     */
    boolean isAttack();

    /**
     * Метод, возвращающий колличество текущих элементов в игре.
     * @return Колличество текущих элементов в игре.
     */
    int getCount();

    /**
     * Метод, возвращающий все текущие элементы игры.
     * @return Все текущие элементы игры.
     */
    Collection<T> getAll();

    /**
     * Метод, возвращающий текущий незактрытый элемент.
     * @return Текущий незактрытый элемент.
     */
    T getSingle();

    /**
     * Метод, возвращающий козырь.
     * @return Козырь.
     */
    Suit getTrump();
}
