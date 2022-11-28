package ru.durak.Kostya.model.abstraction.scene;

import javafx.scene.Group;
import ru.durak.Kostya.infrastructure.Vector;

public interface SceneObject {


    Vector getPosition();

    void setPosition(Vector vector);

    double getRotation();

    void setRotation(double angle);

    int getLayer();

    void setLayer(int layer);

    SceneObject getParent();

    void setParent(SceneObject object);

    Group getGroup();
}
