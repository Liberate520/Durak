package ru.durak.Kostya.model.implementation;

import javafx.scene.image.Image;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.game.enums.Suit;
import ru.durak.Kostya.model.implementation.base.TexturedGameObject;

/**
 * Класс, описывающий карту.
 */
public class CardGameObject extends TexturedGameObject implements CardSceneObject {

    /**
     * Ранг карты.
     */
    private final int rank;

    /**
     * Масть карты.
     */
    private final Suit suit;

    /**
     * Текстура рубашки карты.
     */
    private final Image backTexture;

    /**
     * Текстура карты.
     */
    private Image faceTexture;

    /**
     * Видимость карты.
     */
    private boolean isHiddenFace;

    /**
     * Инициализация объекта карты.
     * @param rank Ранг карты.
     * @param suit Масть карты.
     * @param backTexture Текстура рубашки карты.
     */
    public CardGameObject(int rank, Suit suit, Image backTexture) {
        this.rank = rank;
        this.suit = suit;
        this.backTexture = backTexture;
    }

    /**
     * Метод, возвращающий ранг.
     * @return Ранг карты.
     */
    @Override
    public int getRank() {
        return rank;
    }

    /**
     * Метод, возвращающий масть.
     * @return Масть карты.
     */
    @Override
    public Suit getSuit() {
        return suit;
    }

    /**
     * Метод изменения видимости карты.
     * @param value Значение видимости.
     */
    @Override
    public void isHiddenFace(boolean value) {
        if (value == isHiddenFace)
            return;

        imageView.setImage(value ? backTexture : faceTexture);
        isHiddenFace = value;
    }

    /**
     * Переопределение метода, изменяющего текстуру объекта.
     * @param texture Текстура.
     */
    @Override
    public void setTexture(Image texture) {
        faceTexture = texture;
        if (!isHiddenFace)
            imageView.setImage(texture);
    }
}
