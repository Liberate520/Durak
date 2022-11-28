package ru.durak.Kostya.model.implementation.base;

import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.PlayerSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.abstraction.game.Hand;

public abstract class PlayerGameObject extends GameObject implements PlayerSceneObject<CardSceneObject> {

    public final Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> game;

    protected final Hand<CardSceneObject> cards;

    public PlayerGameObject(Hand<CardSceneObject> cards, Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> game) {
        this.cards = cards;
        this.game = game;
    }

    @Override
    public void add(CardSceneObject newCard) {
        if (newCard == null)
            return;

        order(cards.count());

        Vector indent = Resources.getMetrics().getHorizontalIndent();
        newCard.setLayer(-cards.count());
        newCard.setRotation(0);
        newCard.setPosition(Vector.sum(
                new Vector(-(Resources.getMetrics().getCardSize().getX() + indent.getX() * (cards.count())) / 2, 0),
                Vector.mult(indent, cards.count())));
        newCard.setParent(this);
        cards.add(newCard);
    }

    @Override
    public void remove(CardSceneObject card) {
        cards.remove(card);
        order(cards.count() - 1);
    }

    @Override
    public abstract void getMove();

    @Override
    public int count() {
        return cards.count();
    }

    protected void order(int count) {
        Vector indent = Resources.getMetrics().getHorizontalIndent();
        Vector start = new Vector(-(Resources.getMetrics().getCardSize().getX() + indent.getX() * (count)) / 2, 0);

        int index = 0;
        for (CardSceneObject card: cards) {
            card.setLayer(-index);
            card.setPosition(Vector.sum(start, Vector.mult(indent, index++)));
        }
    }
}
