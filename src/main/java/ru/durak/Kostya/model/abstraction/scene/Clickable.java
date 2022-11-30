package ru.durak.Kostya.model.abstraction.scene;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Объект, взаимодействующий с мышью.
 */
public interface Clickable {

    /**
     * Метод, изменяющий событие при нажатии на объект.
     * @param event Событие.
     */
    void setOnClick(EventHandler<MouseEvent> event);

    /**
     * Метод, изменяющий событие при наведении курсора на объект.
     * @param event Событие.
     */
    void setOnMouseEnter(EventHandler<MouseEvent> event);

    /**
     * Метод, изменяющий событие при отведении курсора с объекта.
     * @param event Событие.
     */
    void setOnMouseExit(EventHandler<MouseEvent> event);
}
