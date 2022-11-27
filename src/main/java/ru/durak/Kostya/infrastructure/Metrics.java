package ru.durak.Kostya.infrastructure;

public class Metrics {

    private final Vector cardSize;

    private final Vector indentHorizontal;

    private final Vector indentVertical;

    private final Vector[] playersPositions;

    private final int cardsCount;

    private final Vector sceneSize;

    public Metrics(int cardsCount, Vector cardSize, Vector[] playersPositions, Vector sceneSize) {
        this.cardsCount = cardsCount;
        this.cardSize = cardSize;
        indentHorizontal = new Vector(cardSize.getX() / 3.3, 0);
        indentVertical = new Vector(0, cardSize.getY() / 2.5);
        this.playersPositions = playersPositions;
        this.sceneSize = sceneSize;
    }

    public Vector getCardSize() {
        return cardSize;
    }

    public Vector getHorizontalIndent() {
        return indentHorizontal;
    }

    public Vector getVerticalIndent() {
        return indentVertical;
    }

    public Vector[] getPlayersPositions() {
        return playersPositions;
    }

    public int getCardsCount() {
        return cardsCount;
    }

    public Vector getSceneSize() {
        return sceneSize;
    }
}
