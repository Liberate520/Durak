package ru.durak.Kostya.model.implementation.base;

import javafx.scene.Group;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;

/**
 * Абстрактный класс, описывающий игровой объект.
 */
public abstract class GameObject implements SceneObject {

    /**
     * Объект родителя.
     */
    private SceneObject parent;

    /**
     * Группа дочерних элементов.
     */
    protected Group group;

    /**
     * Инициализация игрового объекта.
     */
    public GameObject() {
        group = new Group();
    }

    /**
     * Метод, возвращающий позицию объекта.
     * @return Позиция объекта.
     */
    @Override
    public Vector getPosition() {
        return new Vector(group.getTranslateX(), group.getTranslateY());
    }

    /**
     * Метод, изменяющий позицию объекта.
     * @param vector Позиция объекта.
     */
    @Override
    public void setPosition(Vector vector) {
        if (vector == null)
            vector = Vector.zero();

        group.setTranslateX(vector.getX());
        group.setTranslateY(vector.getY());
    }

    /**
     * Метод, изменяющий угол поворота объекта.
     * @param angle Угол поворота объекта.
     */
    @Override
    public void setRotation(double angle) {
        group.setRotate(angle);
    }

    /**
     * Метод, задающий позицию объекта в глубину.
     * @param layer Позицию объекта в глубину.
     */
    @Override
    public void setLayer(int layer) {
        group.setViewOrder(layer);
    }

    /**
     * Метод, изменяющий родительский объект.
     * @param object Родительский объект.
     */
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

    /**
     * Метод, возвращающий группу дочерних элементов.
     * @return Группа дочерних элементлов.
     */
    @Override
    public Group getGroup() {
        return group;
    }
}
