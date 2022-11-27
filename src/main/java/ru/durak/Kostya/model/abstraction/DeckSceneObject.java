package ru.durak.Kostya.model.abstraction;

import ru.durak.Kostya.model.abstraction.game.Deck;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;

public interface DeckSceneObject extends Deck<CardSceneObject>, SceneObject { }
