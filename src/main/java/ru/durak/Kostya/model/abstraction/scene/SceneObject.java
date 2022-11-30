package ru.durak.Kostya.model.abstraction.scene;

import javafx.scene.Group;
import ru.durak.Kostya.infrastructure.Vector;

/**
 * Объект сцены.
 */
public interface SceneObject {

    /**
     * Метод, возвращающий позицию объекта.
     * @return Позиция объекта.
     */
    Vector getPosition();

    /**
     * Метод, изменяющий позицию объекта.
     * @param vector Позиция объекта.
     */
    void setPosition(Vector vector);

    /**
     * Метод, изменяющий угол поворота объекта.
     * @param angle Угол поворота объекта.
     */
    void setRotation(double angle);

    /**
     * Метод, задающий позицию объекта в глубину.
     * @param layer Позицию объекта в глубину.
     */
    void setLayer(int layer);

    /**
     * Метод, изменяющий родительский объект.
     * @param object Родительский объект.
     */
    void setParent(SceneObject object);

    /**
     * Метод, возвращающий группу дочерних элементов.
     * @return Группа дочерних элементлов.
     */
    Group getGroup();
}
