package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.abstraction.game.Hand;

public class NpcPlayerGameObject extends PlayerGameObject {
    public NpcPlayerGameObject(Hand<CardSceneObject> cards, Game<CardSceneObject> game) {
        super(cards, game);
    }
}
