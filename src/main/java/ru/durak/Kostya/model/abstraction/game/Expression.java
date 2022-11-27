package ru.durak.Kostya.model.abstraction.game;

@FunctionalInterface
public interface Expression<TValue, TResult> {
    TResult func(TValue value);
}
