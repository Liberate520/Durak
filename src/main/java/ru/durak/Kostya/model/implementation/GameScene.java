package ru.durak.Kostya.model.implementation;

import javafx.scene.image.Image;

import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;
import ru.durak.Kostya.model.implementation.base.TexturedGameObject;

/**
 * Класс, описывающий объект сцены.
 */
public class GameScene extends TexturedGameObject {

    /**
     * Размер сцены.
     */
    private final Vector size;

    /**
     * Инициализация объекта сцены.
     * @param size Размер сцены.
     */
    public GameScene(Vector size) {
        this.size = size;
    }

    /**
     * Переопределение метода, изменяющий текстуру объекта.
     * @param texture Текстура.
     */
    @Override
    public void setTexture(Image texture) {
        super.setTexture(texture);
        if (imageView.getImage() != null) {
            imageView.setTranslateX((size.getX() - imageView.getImage().getWidth()) / 2);
            imageView.setTranslateY((size.getY() - imageView.getImage().getHeight()) / 2);
        }
    }

    /**
     * Переопределение метода, изменяющего родительский объект.
     * @param object Родительский объект.
     */
    @Override
    public void setParent(SceneObject object) { }

    /**
     * Переопределение метода, изменяющего позицию объекта.
     * @param vector Позиция объекта.
     */
    @Override
    public void setPosition(Vector vector) { }
}
