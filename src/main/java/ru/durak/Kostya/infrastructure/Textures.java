package ru.durak.Kostya.infrastructure;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Textures {

    private final Map<String, Image> images;

    public Textures() {
        images = new HashMap<>();
    }

    public Image getTexture(String key) {
        return key == null || !images.containsKey(key) ? null : images.get(key);
    }

    public void addTexture(String key, Image image) {
        if (key == null || image == null)
            return;

        images.put(key, image);
    }
}
