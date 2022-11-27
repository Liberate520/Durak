package ru.durak.Kostya.model.abstraction.game;

import java.util.Iterator;

public interface Table<T> {

    boolean hasSingle();

    void add(T value);

    T peek();

    void clear();

    Iterator<T> popAll();

    Iterator<T> peekAll();

    int count();
}
