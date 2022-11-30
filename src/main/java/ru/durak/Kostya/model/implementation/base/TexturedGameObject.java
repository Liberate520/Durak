package ru.durak.Kostya.model.implementation.base;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ru.durak.Kostya.model.abstraction.scene.Texturable;

import java.time.temporal.Temporal;

public abstract class TexturedGameObject extends ClickableGameObject implements Texturable {

    /**
     * Объект, содержащий текстуру.
     */
    protected ImageView imageView;

    /**
     * Инициализация текстурируемого объекта.
     */
    public TexturedGameObject() {
        super();
        imageView = new ImageView();
        getGroup().getChildren().addAll(imageView);
    }

    /**
     * Метод, изменяющий текстуру объекта.
     * @param texture Текстура.
     */
    @Override
    public void setTexture(Image texture) {
        imageView.setImage(texture);
    }

    /**
     * Переопределение метода, возвращающего объект, взаимодействующий с мышью.
     * @return Объект, взаимодействующий с мышью.
     */
    @Override
    protected Node getNode() {
        return imageView;
    }
}
