package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.model.abstraction.game.Card;
import ru.durak.Kostya.model.abstraction.game.Expression;
import ru.durak.Kostya.model.abstraction.game.Hand;

import java.util.*;

public class CardHand<TCard extends Card> implements Hand<TCard> {
    private final Set<TCard> cards;

    public CardHand() {
        cards = new TreeSet<>((o1, o2) -> {
            int result = o1.getRank() - o2.getRank();
            return result != 0 ? result : o2.getSuit().compareTo(o1.getSuit());
        });
    }

    @Override
    public TCard first(Expression<TCard, Boolean> predicate) {
        for (TCard card: cards)
            if (Boolean.TRUE.equals(predicate.func(card)))
                return card;

        return null;
    }

    @Override
    public Collection<TCard> where(Expression<TCard, Boolean> predicate) {
        Collection<TCard> collection = new ArrayList<>();

        for (TCard card: cards)
            if (Boolean.TRUE.equals(predicate.func(card)))
                collection.add(card);

        return collection;
    }

    @Override
    public void add(TCard card) {
        if (card != null)
            cards.add(card);
    }

    @Override
    public void remove(TCard card) {
        if (contains(card))
            cards.remove(card);
    }

    @Override
    public boolean contains(TCard card) {
        return card != null && cards.contains(card);
    }

    @Override
    public int count() {
        return cards.size();
    }

    @Override
    public Iterator<TCard> iterator() {
        return cards.iterator();
    }
}