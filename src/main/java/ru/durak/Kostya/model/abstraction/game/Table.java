package ru.durak.Kostya.model.abstraction.game;

import java.util.Collection;

public interface Table<T> extends Iterable<T> {

    boolean hasSingle();

    void add(T value);

    T peek();

    T first(Expression<T, Boolean> predicate);

    void remove(T value);

    void clear();

    Collection<T> popAll();

    Collection<T> getAll();

    int count();
}
