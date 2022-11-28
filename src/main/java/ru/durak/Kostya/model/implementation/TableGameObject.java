package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.TableSceneObject;
import ru.durak.Kostya.model.abstraction.game.Expression;
import ru.durak.Kostya.model.implementation.base.GameObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TableGameObject extends GameObject implements TableSceneObject<CardSceneObject> {

    public List<CardSceneObject> down;

    public List<CardSceneObject> up;

    private final Vector start;

    private final Vector cardSize;

    public TableGameObject() {
        down = new ArrayList<>();
        up = new ArrayList<>();
        int defaultCards = Resources.getMetrics().getCardsCount();
        cardSize = Resources.getMetrics().getCardSize();

        double x = -(cardSize.getX() * defaultCards + Resources.getMetrics().getHorizontalIndent().getX() *
                (defaultCards - 1)) / 2;

        double y = -(cardSize.getY() + Resources.getMetrics().getVerticalIndent().getY()) / 2;

        start = new Vector(x, y);
    }

    @Override
    public boolean hasSingle() {
        return down.size() != up.size();
    }

    @Override
    public void add(CardSceneObject newCard) {
        if (newCard == null)
            return;

        Vector horizontalIndent = Resources.getMetrics().getHorizontalIndent();
        Vector verticalIndent = Resources.getMetrics().getVerticalIndent();

        Vector position = new Vector(
                start.getX() + (cardSize.getX() + horizontalIndent.getX()) * (hasSingle() ? count() - 1 : count()),
                start.getY() + (!hasSingle() ? 0 : verticalIndent.getY())
        );

        if (hasSingle())
            up.add(newCard);
        else
            down.add(newCard);

        newCard.setParent(this);
        newCard.setPosition(position);
        newCard.isHiddenFace(false);
    }

    @Override
    public CardSceneObject peek() {
        if (count() == 0)
            return null;

        return hasSingle() ? down.get(down.size() - 1) : up.get(up.size() - 1);
    }

    @Override
    public CardSceneObject first(Expression<CardSceneObject, Boolean> predicate) {
        for (CardSceneObject card: this)
            if (predicate.func(card))
                return card;

        return null;
    }

    @Override
    public void clear() {
        for (CardSceneObject card: up)
            card.setParent(null);

        up.clear();

        for (CardSceneObject card: down)
            card.setParent(null);

        down.clear();
    }

    @Override
    public Collection<CardSceneObject> popAll() {
        Collection<CardSceneObject> result = new ArrayList<>(down);
        result.addAll(up);
        return result;
    }

    @Override
    public Collection<CardSceneObject> getAll() {
        Collection<CardSceneObject> result = new ArrayList<>(down);
        result.addAll(up);
        return result;
    }

    @Override
    public int count() {
        return down.size();
    }

    @Override
    public Iterator<CardSceneObject> iterator() {
        return new TableIterator(true);
    }

    private class TableIterator implements Iterator<CardSceneObject> {

        private final boolean safe;

        private boolean first;

        private int index;

        public TableIterator(boolean safe) {
            this.safe = safe;
            index = down.size() - 1;
            first = !hasSingle();
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public CardSceneObject next() {
            CardSceneObject card;
            if (first) {
                card = up.get(index);
                if (!safe)
                    up.remove(card);
            } else {
                card = down.get(index);
                if (!safe)
                    down.remove(card);
            }
            first = !first;
            index--;
            return card;
        }
    }
}
