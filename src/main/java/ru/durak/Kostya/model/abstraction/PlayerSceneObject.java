package ru.durak.Kostya.model.abstraction;

import ru.durak.Kostya.model.abstraction.game.Card;
import ru.durak.Kostya.model.abstraction.game.Player;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;

public interface PlayerSceneObject<TCard extends Card> extends Player<TCard>, SceneObject {
    boolean getActiveButton();
}
