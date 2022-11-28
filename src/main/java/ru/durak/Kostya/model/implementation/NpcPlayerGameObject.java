package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.PlayerSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.abstraction.game.Hand;
import ru.durak.Kostya.model.implementation.base.PlayerGameObject;

public class NpcPlayerGameObject extends PlayerGameObject {
    public NpcPlayerGameObject(Hand<CardSceneObject> cards, Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> game) {
        super(cards, game);
    }

    @Override
    public void getMove() {

    }

    @Override
    public boolean getActiveButton() {
        return false;
    }
}
