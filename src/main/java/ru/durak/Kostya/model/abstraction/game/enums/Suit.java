package ru.durak.Kostya.model.abstraction.game.enums;

public enum Suit {
    hearts(0),
    tiles(1),
    clovers(2),
    pikes(3);

    private final int value;

    private Suit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
