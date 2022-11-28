package ru.durak.Kostya.model.abstraction.game;

import java.util.Collection;

public interface Hand<TCard extends Card> extends Iterable<TCard> {

    TCard first(Expression<TCard, Boolean> predicate);

    Collection<TCard> where(Expression<TCard, Boolean> predicate);

    void add(TCard value);

    void remove(TCard value);

    boolean contains(TCard value);

    int count();
}