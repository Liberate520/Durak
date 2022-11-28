package ru.durak.Kostya.model.abstraction.builders;

import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.PlayerSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;

import java.util.List;

public interface PlayerBuilder {
    List<PlayerSceneObject<CardSceneObject>> build(Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> game);
}
