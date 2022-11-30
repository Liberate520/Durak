package ru.durak.Kostya.infrastructure;

/**
 * Класс, описывающий вектор.
 */
public class Vector {

    /**
     * Позиции на оси координат.
     */
    private final double x, y;

    /**
     * Инициализация объекта вектора.
     * @param x Позиция по горизонтали.
     * @param y Позиция по вертикали.
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод, возвращающий позицию по горизонтали.
     * @return Позиция по горизонтали.
     */
    public double getX() {
        return x;
    }

    /**
     * Метод, возвращающий позицию по вертиуали.
     * @return Позиция по вертикали.
     */
    public double getY() {
        return y;
    }

    /**
     * Статический метод сложения двух векторов.
     * @param left Первое слагаемое.
     * @param right Второе слагаемое.
     * @return Сумма векторов.
     */
    public static Vector sum(Vector left, Vector right) {
        if (left == null || right == null)
            return null;

        return new Vector(left.x + right.x, left.y + right.y);
    }

    /**
     * Статический метод разности двух векторов.
     * @param left Уменьшаемый вектор.
     * @param right Вычитаемый вектор.
     * @return Разность векторов.
     */
    public static Vector diff(Vector left, Vector right) {
        if (left == null || right == null)
            return null;

        return new Vector(left.x - right.x, left.y - right.y);
    }

    /**
     * Статический метод умножения вектора на число.
     * @param vector Вектор.
     * @param multiplier Множитель.
     * @return Произведение.
     */
    public static Vector mult(Vector vector, double multiplier) {
        if (vector == null)
            return null;

        return new Vector(vector.x * multiplier, vector.y * multiplier);
    }

    /**
     * Статический метод деления вектора на число.
     * @param vector Вектор.
     * @param divisor Делитель.
     * @return Частное.
     */
    public static Vector div(Vector vector, double divisor) {
        if (vector == null || divisor == 0)
            return null;

        return new Vector(vector.x / divisor, vector.y / divisor);
    }

    /**
     * Статический метод, возвращающий нулевой вектор.
     * @return Нулевой вектор.
     */
    public static Vector zero() {
        return new Vector(0, 0);
    }
}
