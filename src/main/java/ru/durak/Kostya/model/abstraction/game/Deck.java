package ru.durak.Kostya.model.abstraction.game;

public interface Deck<T> {

    void push(T value);

    T pop();

    T peekLast();

    int count();

    void shuffle();
}