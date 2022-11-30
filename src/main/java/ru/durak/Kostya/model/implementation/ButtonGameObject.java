package ru.durak.Kostya.model.implementation;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import ru.durak.Kostya.model.abstraction.ButtonSceneObject;
import ru.durak.Kostya.model.implementation.base.ClickableGameObject;

/**
 * Класс, описывающий кнопку.
 */
public class ButtonGameObject extends ClickableGameObject implements ButtonSceneObject {

    /**
     * Объект кнопки.
     */
    protected Button button;

    /**
     * Инициализация объекта кнопки.
     * @param label Текст кнопки.
     */
    public ButtonGameObject(String label) {
        super();
        button = new Button(label);
        getGroup().getChildren().addAll(button);
    }

    /**
     * Переопределение метода, возвращающего объект, взаимодействующий с мышью.
     * @return Объект, взаимодействующий с мышью.
     */
    @Override
    protected Node getNode() {
        return button;
    }
}
