package ru.durak.Kostya.model.abstraction.scene;

import javafx.scene.Group;
import javafx.scene.image.Image;
import ru.durak.Kostya.infrastructure.Vector;

public interface SceneObject {

    Group getGroup();

    Vector getPosition();

    void setPosition(Vector vector);

    double getRotation();

    void setRotation(double angle);

    int getLayer();

    void setLayer(int layer);

    Image getTexture();

    void setTexture(Image texture);

    SceneObject getParent();

    void setParent(SceneObject object);
}
