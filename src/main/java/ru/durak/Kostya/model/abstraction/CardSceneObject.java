package ru.durak.Kostya.model.abstraction;

import ru.durak.Kostya.model.abstraction.game.Card;
import ru.durak.Kostya.model.abstraction.scene.Clickable;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;
import ru.durak.Kostya.model.abstraction.scene.Texturable;

public interface CardSceneObject extends Card, SceneObject, Texturable, Clickable { }
