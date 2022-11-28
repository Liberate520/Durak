package ru.durak.Kostya.model.abstraction.game;

public interface Player<T> {
    void add(T value);

    void remove(T value);

    void getMove();

    int count();
}
