package ru.durak.Kostya.infrastructure;

/**
 * Класс, описывающий хранилище метрик проекта.
 */
public class Metrics {

    /**
     * Векторы с измерениями объектов проекта.
     */
    private final Vector sceneSize, cardSize, indentHorizontal, indentVertical;

    /**
     * Позиции и углы поворота игроков.
     */
    private final Transform[] playersTransform;

    /**
     * Колличество карт по умолчанию.
     */
    private final int cardsCount;

    /**
     * Инициализация объекта, хранящего метрики проекта.
     * @param cardsCount Колличество карт по умолчанию.
     * @param cardSize Размер карты.
     * @param playersTransform Позиции и углы поворота игроков.
     * @param sceneSize Размер сцены.
     */
    public Metrics(int cardsCount, Vector cardSize, Transform[] playersTransform, Vector sceneSize) {
        this.cardsCount = cardsCount;
        this.cardSize = cardSize;
        indentHorizontal = new Vector(cardSize.getX() / 3.3, 0);
        indentVertical = new Vector(0, cardSize.getY() / 2.5);
        this.playersTransform = playersTransform;
        this.sceneSize = sceneSize;
    }

    /**
     * Метод, возвращающий размер карты.
     * @return Размер карты.
     */
    public Vector getCardSize() {
        return cardSize;
    }

    /**
     * Метод, возвращающий горизонтальный выступ карты.
     * @return Горизонтальный выступ карты.
     */
    public Vector getHorizontalIndent() {
        return indentHorizontal;
    }

    /**
     * Метод, возвращающий вертикальный выступ карты.
     * @return Вертикальный выступ карты.
     */
    public Vector getVerticalIndent() {
        return indentVertical;
    }

    /**
     * Метод, возвращающий позиции и углы поворота игроков.
     * @return Позиции и углы поворота игроков.
     */
    public Transform[] getPlayersTransform() {
        return playersTransform;
    }

    /**
     * Метод, возвращающий колличество карт по умолчанию.
     * @return Колличество карт по умолчанию.
     */
    public int getCardsCount() {
        return cardsCount;
    }

    /**
     * Метод, возвращающий размер сцены.
     * @return Размер сцены.
     */
    public Vector getSceneSize() {
        return sceneSize;
    }
}
