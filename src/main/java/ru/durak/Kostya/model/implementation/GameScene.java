package ru.durak.Kostya.model.implementation;

import javafx.scene.image.Image;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;
import ru.durak.Kostya.model.implementation.base.TexturedGameObject;

public class GameScene extends TexturedGameObject {

    private final Vector size;

    public GameScene(Vector size) {
        this.size = size;
    }

    @Override
    public void setTexture(Image texture) {
        super.setTexture(texture);
        if (imageView.getImage() != null) {
            imageView.setTranslateX((size.getX() - imageView.getImage().getWidth()) / 2);
            imageView.setTranslateY((size.getY() - imageView.getImage().getHeight()) / 2);
        }
    }

    @Override
    public void setParent(SceneObject object) { }

    @Override
    public void setPosition(Vector vector) { }
}
