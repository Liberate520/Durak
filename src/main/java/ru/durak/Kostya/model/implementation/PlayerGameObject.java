package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.PlayerSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.abstraction.game.Hand;

public class PlayerGameObject extends GameObject implements PlayerSceneObject<CardSceneObject> {

    public final Game<CardSceneObject> game;

    protected final Hand<CardSceneObject> cards;

    public PlayerGameObject(Hand<CardSceneObject> cards, Game<CardSceneObject> game) {
        this.cards = cards;
        this.game = game;
    }

    @Override
    public void add(CardSceneObject newCard) {
        if (newCard == null)
            return;

        Vector indent = Resources.getMetrics().getHorizontalIndent();

        Vector start = new Vector(-(Resources.getMetrics().getCardSize().getX() + indent.getX() * (cards.count())) / 2, 0);

        int index = 0;
        for (CardSceneObject card: cards) {
            card.setLayer(-index);
            card.setPosition(Vector.sum(start, Vector.mult(indent, index++)));
        }

        newCard.setLayer(-index);
        newCard.setPosition(Vector.sum(start, Vector.mult(indent, index)));
        newCard.setParent(this);
        cards.add(newCard);
    }

    @Override
    public int count() {
        return cards.count();
    }
}
