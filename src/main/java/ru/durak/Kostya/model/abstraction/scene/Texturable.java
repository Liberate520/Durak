package ru.durak.Kostya.model.abstraction.scene;

import javafx.scene.image.Image;

/**
 * Текстурируемый объект.
 */
public interface Texturable {

    /**
     * Метод, изменяющий текстуру объекта.
     * @param texture Текстура.
     */
    void setTexture(Image texture);
}
