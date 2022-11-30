package ru.durak.Kostya.model.abstraction.game;

@FunctionalInterface
public interface Predicate<T, U> {
    U func(T value);
}
