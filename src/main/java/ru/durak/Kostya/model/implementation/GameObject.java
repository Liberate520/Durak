package ru.durak.Kostya.model.implementation;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;

public abstract class GameObject implements SceneObject {

    protected ImageView imageView;

    private SceneObject parent;

    protected Group group;

    public GameObject() {
        group = new Group();
        imageView = new ImageView();
        group.getChildren().addAll(imageView);
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
    public int getLayer() {
        return (int)group.getViewOrder();
    }

    @Override
    public void setLayer(int layer) {
        group.setViewOrder(layer);
    }

    @Override
    public void setTexture(Image texture) {
        imageView.setImage(texture);
    }

    @Override
    public Image getTexture() {
        return imageView.getImage();
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
