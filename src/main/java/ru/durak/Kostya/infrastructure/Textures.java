package ru.durak.Kostya.infrastructure;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, описывающий хранилище текстур проекта.
 */
public class Textures {

    /**
     * Мэп, хранящий текстуры.
     */
    private final Map<String, Image> images;

    /**
     * Инициализация хранилища текстур.
     */
    public Textures() {
        images = new HashMap<>();
    }

    /**
     * Метод, возвращающий текстуру по ключу.
     * @param key Ключ текстуры.
     * @return Текстура.
     */
    public Image getTexture(String key) {
        return key == null || !images.containsKey(key) ? null : images.get(key);
    }

    /**
     * Метод, добавляющий текстуру по ключу.
     * @param key Ключ текстуры.
     * @param image Текстура.
     */
    public void addTexture(String key, Image image) {
        if (key == null || image == null)
            return;

        images.put(key, image);
    }
}
