package ru.durak.Kostya.model.abstraction.game;

/**
 * Игрок.
 * @param <T> Тип элементов игры.
 */
public interface Player<T> {

    /**
     * Метод добавления элемента игроку.
     * @param value Добавляемый элемент.
     */
    void add(T value);

    /**
     * Метод удаления элемента у игрока.
     * @param value Удаляемый элемент.
     */
    void remove(T value);

    /**
     * Метод передачи хода игроку.
     */
    void getMove();

    /**
     * Метод, возвращающий колличество элементов у игрока.
     * @return Колличество элементов у игрока.
     */
    int count();
}
