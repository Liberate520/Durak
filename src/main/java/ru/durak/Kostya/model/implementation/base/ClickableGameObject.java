package ru.durak.Kostya.model.implementation.base;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import ru.durak.Kostya.model.abstraction.scene.Clickable;

public abstract class ClickableGameObject extends GameObject implements Clickable {

    public void setOnClick(EventHandler<MouseEvent> event) {
        getNode().setOnMouseClicked(event);
    }

    protected Node getNode() {
        return getGroup();
    }
}
