package ru.durak.Kostya.model.abstraction.game;

/**
 * Класс, описывающий хранилище элементов игрока.
 * @param <T> Тип элемента.
 */
public interface Hand<T> extends Iterable<T> {

    /**
     * Метод, возвращающий первое вхождение.
     * @return Первое вхождение.
     */
    T first();

    /**
     * Метод, возвращающий первое вхождение, удовлетворяющее условию.
     * @param predicate Условие.
     * @return Первое вхождение, удовлетворяющее условию.
     */
    T first(Predicate<T, Boolean> predicate);

    /**
     * Метод, добавляющий элемент в хранилище.
     * @param value Добавляемый элемент.
     */
    void add(T value);

    /**
     * Метод, удаляющий элемент из хранилища.
     * @param value Удаляемый элемент.
     */
    void remove(T value);

    /**
     * Метод проверки наличия элемента в харнилище.
     * @param value Элемент для проверки.
     * @return Результат проверки.
     */
    boolean contains(T value);

    /**
     * Метод, возвращающий колличество элементов в хранилище.
     * @return Колличество элементов в хранилище.
     */
    int count();
}