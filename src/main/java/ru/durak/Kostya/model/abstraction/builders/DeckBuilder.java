package ru.durak.Kostya.model.abstraction.builders;

import ru.durak.Kostya.model.abstraction.DeckSceneObject;

/**
 * Фабрика по созданию колоды.
 */
public interface DeckBuilder {

    /**
     * Метод, возвращающий колоду.
     * @return Созданная колода.
     */
    DeckSceneObject build();
}
