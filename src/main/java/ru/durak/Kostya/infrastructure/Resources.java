package ru.durak.Kostya.infrastructure;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Resources {

    private Resources() { }

    private static Textures textures;

    private static Metrics metrics;

    public static Textures getTextures() {
        return textures;
    }

    public static void setTextures(Textures textures) {
        Resources.textures = textures;
    }

    public static Metrics getMetrics() {
        return metrics;
    }

    public static void setMetrics(Metrics metrics) {
        Resources.metrics = metrics;
    }
}
