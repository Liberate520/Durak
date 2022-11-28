package ru.durak.Kostya.infrastructure;

public class Transform {

    private final Vector position;

    private final double rotation;

    public Transform(Vector position, double rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector getPosition() {
        return position;
    }

    public double getRotation() {
        return rotation;
    }
}
