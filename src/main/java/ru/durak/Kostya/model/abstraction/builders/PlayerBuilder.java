package ru.durak.Kostya.model.abstraction.builders;

import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.PlayerSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;

import java.util.List;

/**
 * Фабрика, создающая игроков.
 */
public interface PlayerBuilder {

    /**
     * Метод, возвращающий список игроков.
     * @param game Ссылка на игру.
     * @return Список созданных игроков.
     */
    List<PlayerSceneObject<CardSceneObject>> build(Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> game);
}
