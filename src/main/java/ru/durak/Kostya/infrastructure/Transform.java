package ru.durak.Kostya.infrastructure;

/**
 * Класс, описывающий объект, хранящий позицию и угол поворота.
 */
public class Transform {

    /**
     * Позиция.
     */
    private final Vector position;

    /**
     * Угол поворота.
     */
    private final double rotation;

    /**
     * Инициализация объекта.
     * @param position Позиция.
     * @param rotation Угол поворота.
     */
    public Transform(Vector position, double rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * Метод, возвращающий позицию.
     * @return Позиция.
     */
    public Vector getPosition() {
        return position;
    }

    /**
     * Метод, возвращающий угол поворота.
     * @return Угол поворота.
     */
    public double getRotation() {
        return rotation;
    }
}
