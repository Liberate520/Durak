package ru.durak.Kostya.model.implementation;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.game.enums.Suit;
import ru.durak.Kostya.model.implementation.base.GameObject;
import ru.durak.Kostya.model.implementation.base.TexturedGameObject;

public class CardGameObject extends TexturedGameObject implements CardSceneObject {

    private final int rank;

    private final Suit suit;

    private final Image backTexture;

    private Image faceTexture;

    private boolean isHiddenFace;

    public CardGameObject(int rank, Suit suit, Image backTexture) {
        this.rank = rank;
        this.suit = suit;
        this.backTexture = backTexture;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean isHiddenFace() {
        return isHiddenFace;
    }

    @Override
    public void isHiddenFace(boolean value) {
        if (value == isHiddenFace)
            return;

        imageView.setImage(value ? backTexture : faceTexture);
        isHiddenFace = value;
    }

    @Override
    public void setTexture(Image texture) {
        faceTexture = texture;
        if (!isHiddenFace)
            imageView.setImage(texture);
    }

    @Override
    public Image getTexture() {
        return faceTexture;
    }

    @Override
    public void setOnClick(EventHandler<MouseEvent> event) {
        imageView.setOnMouseClicked(event);
    }
}
