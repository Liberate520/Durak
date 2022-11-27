package ru.durak.Kostya.model.abstraction.game;

import ru.durak.Kostya.model.abstraction.game.enums.Suit;

public interface Card {

    int getRank();

    Suit getSuit();

    boolean isHiddenFace();

    void isHiddenFace(boolean value);
}
