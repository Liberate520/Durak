package ru.durak.Kostya.model.abstraction.scene;

import ru.durak.Kostya.infrastructure.Vector;

public interface Positionable {

    Vector getPosition();

    void setPosition(Vector vector);
}
