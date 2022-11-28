package ru.durak.Kostya.model.implementation.base;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.durak.Kostya.model.abstraction.scene.Texturable;

import java.time.temporal.Temporal;

public abstract class TexturedGameObject extends ClickableGameObject implements Texturable {

    protected ImageView imageView;

    public TexturedGameObject() {
        super();
        imageView = new ImageView();
        getGroup().getChildren().addAll(imageView);
    }

    @Override
    public void setTexture(Image texture) {
        imageView.setImage(texture);
    }

    @Override
    public Image getTexture() {
        return imageView.getImage();
    }

    @Override
    protected Node getNode() {
        return imageView;
    }
}
