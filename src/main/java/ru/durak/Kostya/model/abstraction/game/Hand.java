package ru.durak.Kostya.model.abstraction.game;

import java.util.Collection;

public interface Hand<T> extends Iterable<T> {

    T first(Expression<T, Boolean> predicate);

    Collection<T> where(Expression<T, Boolean> predicate);

    void add(T value);

    void remove(T value);

    boolean contains(T value);

    int count();
}