package ru.durak.Kostya.model.abstraction.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public interface Clickable {
    void setOnClick(EventHandler<MouseEvent> event);
}
