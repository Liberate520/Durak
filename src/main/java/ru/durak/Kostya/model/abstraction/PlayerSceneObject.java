package ru.durak.Kostya.model.abstraction;

import ru.durak.Kostya.model.abstraction.game.Player;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;

public interface PlayerSceneObject<T> extends Player<T>, SceneObject { }
