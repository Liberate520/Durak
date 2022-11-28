package ru.durak.Kostya.model.implementation.base;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;

public abstract class GameObject implements SceneObject {

    private SceneObject parent;

    protected Group group;

    public GameObject() {
        group = new Group();
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public Vector getPosition() {
        return new Vector(group.getTranslateX(), group.getTranslateY());
    }

    @Override
    public void setPosition(Vector vector) {
        if (vector == null)
            vector = Vector.zero();

        group.setTranslateX(vector.getX());
        group.setTranslateY(vector.getY());
    }

    @Override
    public double getRotation() {
        return group.getRotate();
    }

    @Override
    public void setRotation(double angle) {
        group.setRotate(angle);
    }

    @Override
    public int getLayer() {
        return (int)group.getViewOrder();
    }

    @Override
    public void setLayer(int layer) {
        group.setViewOrder(layer);
    }

    @Override
    public SceneObject getParent() {
        return parent;
    }

    @Override
    public void setParent(SceneObject object) {
        if (object == parent)
            return;

        if (parent != null && parent.getGroup() != null)
            parent.getGroup().getChildren().remove(group);

        if (object != null && object.getGroup() != null)
            object.getGroup().getChildren().add(group);

        parent = object;
    }
}
