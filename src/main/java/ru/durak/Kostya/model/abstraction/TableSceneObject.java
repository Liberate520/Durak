package ru.durak.Kostya.model.abstraction;

import ru.durak.Kostya.model.abstraction.game.Card;
import ru.durak.Kostya.model.abstraction.game.Table;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;

public interface TableSceneObject<TCard extends Card> extends Table<TCard>, SceneObject { }
