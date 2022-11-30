package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.model.abstraction.game.Card;
import ru.durak.Kostya.model.abstraction.game.Hand;
import ru.durak.Kostya.model.abstraction.game.Predicate;

import java.util.*;

/**
 * Класс, описывающий хранилище карт игрока.
 * @param <TCard> Тип карты.
 */
public class CardHand<TCard extends Card> implements Hand<TCard> {

    /**
     * Сэт, хранящий карты игрока.
     */
    private final Set<TCard> cards;

    /**
     * Инициализация хранилища карт.
     */
    public CardHand() {
        cards = new TreeSet<>((o1, o2) -> {
            int result = o1.getRank() - o2.getRank();
            return result != 0 ? result : o2.getSuit().compareTo(o1.getSuit());
        });
    }

    /**
     * Метод, возвращающий первое вхождение.
     * @return Первое вхождение.
     */
    @Override
    public TCard first() {
        for (TCard card: cards)
            return card;

        return null;
    }

    /**
     * Метод, возвращающий первое вхождение, удовлетворяющее условию.
     * @param predicate Условие.
     * @return Первое вхождение, удовлетворяющее условию.
     */
    @Override
    public TCard first(Predicate<TCard, Boolean> predicate) {
        for (TCard card: cards)
            if (Boolean.TRUE.equals(predicate.func(card)))
                return card;

        return null;
    }

    /**
     * Метод, добавляющий карту в хранилище.
     * @param card Добавляемая карта.
     */
    @Override
    public void add(TCard card) {
        if (card != null)
            cards.add(card);
    }

    /**
     * Метод, удаляющий карту из хранилища.
     * @param card Удаляемая карта.
     */
    @Override
    public void remove(TCard card) {
        if (contains(card))
            cards.remove(card);
    }

    /**
     * Метод проверки наличия карты в харнилище.
     * @param card Карта для проверки.
     * @return Результат проверки.
     */
    @Override
    public boolean contains(TCard card) {
        return card != null && cards.contains(card);
    }

    /**
     * Метод, возвращающий колличество карт в хранилище.
     * @return Колличество карт в хранилище.
     */
    @Override
    public int count() {
        return cards.size();
    }

    /**
     * Метод, возвращающий итератор.
     * @return Итератор.
     */
    @Override
    public Iterator<TCard> iterator() {
        return cards.iterator();
    }
}