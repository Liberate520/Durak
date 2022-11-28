package ru.durak.Kostya.model.implementation;

import javafx.scene.Node;
import javafx.scene.control.Button;
import ru.durak.Kostya.model.abstraction.ButtonSceneObject;
import ru.durak.Kostya.model.implementation.base.ClickableGameObject;

public class ButtonGameObject extends ClickableGameObject implements ButtonSceneObject {

    protected Button button;

    public ButtonGameObject(String label) {
        super();
        button = new Button(label);
        getGroup().getChildren().addAll(button);
    }

    @Override
    public String getLabel() {
        return button.getText();
    }

    @Override
    public void setLabel(String value) {
        button.setText(value);
    }

    @Override
    protected Node getNode() {
        return button;
    }
}
