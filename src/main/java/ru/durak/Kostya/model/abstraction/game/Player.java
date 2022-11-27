package ru.durak.Kostya.model.abstraction.game;

public interface Player<T> {
    void add(T card);

    int count();
}
