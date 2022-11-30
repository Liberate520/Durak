package ru.durak.Kostya.model.implementation.base;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import ru.durak.Kostya.model.abstraction.scene.Clickable;

/**
 * Класс, описывающий игровой объект, взаимодействующий с мышью.
 */
public abstract class ClickableGameObject extends GameObject implements Clickable {

    /**
     * Метод, изменяющий событие при нажатии на объект.
     * @param event Событие.
     */
    @Override
    public void setOnClick(EventHandler<MouseEvent> event) {
        getNode().setOnMouseClicked(event);
    }

    /**
     * Метод, изменяющий событие при наведении курсора на объект.
     * @param event Событие.
     */
    @Override
    public void setOnMouseEnter(EventHandler<MouseEvent> event) {
        getNode().setOnMouseEntered(event);
    }

    /**
     * Метод, изменяющий событие при отведении курсора с объекта.
     * @param event Событие.
     */
    @Override
    public void setOnMouseExit(EventHandler<MouseEvent> event) {
        getNode().setOnMouseExited(event);
    }

    /**
     * Метод, возвращающий объект, взаимодействующий с мышью.
     * @return Объект, взаимодействующий с мышью.
     */
    protected Node getNode() {
        return getGroup();
    }
}
