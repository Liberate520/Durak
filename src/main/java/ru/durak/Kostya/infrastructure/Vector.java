package ru.durak.Kostya.infrastructure;

public class Vector {

    private final double x;

    private final double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Vector sum(Vector left, Vector right) {
        if (left == null || right == null)
            return null;

        return new Vector(left.x + right.x, left.y + right.y);
    }

    public static Vector diff(Vector left, Vector right) {
        if (left == null || right == null)
            return null;

        return new Vector(left.x - right.x, left.y - right.y);
    }

    public static Vector mult(Vector vector, double multiplier) {
        if (vector == null)
            return null;

        return new Vector(vector.x * multiplier, vector.y * multiplier);
    }

    public static Vector div(Vector vector, double divisor) {
        if (vector == null || divisor == 0)
            return null;

        return new Vector(vector.x / divisor, vector.y / divisor);
    }

    public static Vector zero() {
        return new Vector(0, 0);
    }

    public static Vector copy(Vector vector) {
        return new Vector(vector.getX(), vector.getY());
    }

    public static double distance(Vector vector) {
        return distance(Vector.zero(), vector);
    }

    public static double distance(Vector start, Vector end) {
        Vector vector = Vector.diff(end, start);
        return Math.abs(Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY()));
    }
}
