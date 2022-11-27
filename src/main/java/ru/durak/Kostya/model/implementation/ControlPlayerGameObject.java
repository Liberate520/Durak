package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.abstraction.game.Hand;

public class ControlPlayerGameObject extends PlayerGameObject {

    public ControlPlayerGameObject(Hand<CardSceneObject> cards, Game<CardSceneObject> game) {
        super(cards, game);
    }

    @Override
    public void add(CardSceneObject newCard) {
        super.add(newCard);
        sort();
        showFaceAll();
    }

    private void sort() {
        if (cards.count() == 0)
            return;

        Vector indent = Resources.getMetrics().getHorizontalIndent();

        Vector start = new Vector(-(Resources.getMetrics().getCardSize().getX() +
                indent.getX() * (cards.count() - 1)) / 2, 0);

        int index = 0;
        for (CardSceneObject card: cards) {
            card.setLayer(-index - 1);
            card.setPosition(Vector.sum(start, Vector.mult(indent, index++)));
        }
    }

    private void showFaceAll() {
        for (CardSceneObject card: cards)
            card.isHiddenFace(false);
    }
}
