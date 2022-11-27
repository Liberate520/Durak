package ru.durak.Kostya.model.abstraction;

import ru.durak.Kostya.model.abstraction.game.Game;

import java.util.List;

public interface PlayerBuilder {
    List<PlayerSceneObject<CardSceneObject>> build(Game<CardSceneObject> game);
}
