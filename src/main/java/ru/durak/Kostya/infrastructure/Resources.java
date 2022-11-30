package ru.durak.Kostya.infrastructure;

/**
 * Статический класс, хранящий ссылки на ресурсы проекта.
 */
public class Resources {

    /**
     * Приватный конструктор класса.
     */
    private Resources() { }

    /**
     * Экземпляр класса, хранящий текстуры.
     */
    private static Textures textures;

    /**
     * Экземпляр класса, хранящий метрики.
     */
    private static Metrics metrics;

    /**
     * Метод, возвращающий экземпляр класса, хранящий текстуры.
     * @return Экземпляр класса, хранящий текстуры.
     */
    public static Textures getTextures() {
        return textures;
    }

    /**
     * Метод, изменяющий экземпляр класса, хранящий текстуры.
     * @param textures Новый экземпляр класса, хранящий текстуры.
     */
    public static void setTextures(Textures textures) {
        Resources.textures = textures;
    }

    /**
     * Метод, возвращающий экземпляр класса, хранящий метрики.
     * @return Экземпляр класса, хранящий метрики.
     */
    public static Metrics getMetrics() {
        return metrics;
    }

    /**
     * Метод, изменяющий экземпляр класса, хранящий метрики.
     * @param metrics Новый экземпляр класса, хранящий метрики.
     */
    public static void setMetrics(Metrics metrics) {
        Resources.metrics = metrics;
    }
}
