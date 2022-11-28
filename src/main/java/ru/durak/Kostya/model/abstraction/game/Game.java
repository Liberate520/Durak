package ru.durak.Kostya.model.abstraction.game;

import ru.durak.Kostya.model.abstraction.PlayerSceneObject;
import ru.durak.Kostya.model.abstraction.game.enums.Suit;

public interface Game<T, TPlayer extends Player<T>> {

    void start();

    void move(TPlayer player, T value);

    void give(TPlayer player);

    void skip(TPlayer player);

    Suit getTrump();
}
