package ru.durak.Kostya.model.abstraction;

import ru.durak.Kostya.model.abstraction.game.Deck;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;

/**
 * Колода карт.
 */
public interface DeckSceneObject extends Deck<CardSceneObject>, SceneObject { }
