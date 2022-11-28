package ru.durak.Kostya.infrastructure;

public class Metrics {

    private final Vector cardSize;

    private final Vector indentHorizontal;

    private final Vector indentVertical;

    private final Transform[] playersTransform;

    private final int cardsCount;

    private final Vector sceneSize;

    public Metrics(int cardsCount, Vector cardSize, Transform[] playersTransform, Vector sceneSize) {
        this.cardsCount = cardsCount;
        this.cardSize = cardSize;
        indentHorizontal = new Vector(cardSize.getX() / 3.3, 0);
        indentVertical = new Vector(0, cardSize.getY() / 2.5);
        this.playersTransform = playersTransform;
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

    public Transform[] getPlayersTransform() {
        return playersTransform;
    }

    public int getCardsCount() {
        return cardsCount;
    }

    public Vector getSceneSize() {
        return sceneSize;
    }
}
