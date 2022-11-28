package ru.durak.Kostya.model.abstraction.scene;

import javafx.scene.Group;

public interface Parentable {

    Group getGroup();

    SceneObject getParent();

    void setParent(SceneObject object);
}
