package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.TableSceneObject;
import ru.durak.Kostya.model.abstraction.game.Predicate;
import ru.durak.Kostya.model.implementation.base.GameObject;

import java.util.*;

/**
 * Класс, описывающий игровой стол.
 */
public class TableGameObject extends GameObject implements TableSceneObject<CardSceneObject> {

    /**
     * Мэп, хранящий карты игрового стола.
     */
    public Map<CardSceneObject, CardSceneObject> cards;

    /**
     * Стартовая позиция и размер карты.
     */
    private final Vector start, cardSize;

    /**
     * Колличество неотбитых карт.
     */
    private int singlesCount;

    /**
     * Инициализация объекта игрового стола.
     */
    public TableGameObject() {
        cards = new LinkedHashMap<>();
        singlesCount = 0;
        int defaultCards = Resources.getMetrics().getCardsCount();
        cardSize = Resources.getMetrics().getCardSize();

        double x = -(cardSize.getX() * defaultCards + Resources.getMetrics().getHorizontalIndent().getX() *
                (defaultCards - 1)) / 2;

        double y = -(cardSize.getY() + Resources.getMetrics().getVerticalIndent().getY()) / 2;

        start = new Vector(x, y);
    }

    /**
     * Метод добавляющий новую неотбитую карту.
     * @param newCard Новая неотбитая карта.
     */
    @Override
    public void addDown(CardSceneObject newCard) {
        if (newCard == null)
            return;

        Vector position = new Vector(
                start.getX() + (cardSize.getX() + Resources.getMetrics().getHorizontalIndent().getX()) * count(),
                start.getY()
        );

        newCard.setLayer(-1);
        newCard.setParent(this);
        newCard.setPosition(position);
        newCard.isHiddenFace(false);

        cards.put(newCard, null);
        singlesCount++;
    }

    /**
     * Метод добавляющий отбивающую карту.
     * @param card Отбивающая карта.
     */
    @Override
    public void addUp(CardSceneObject card) {
        if (card == null || singlesCount == 0)
            return;

        for (CardSceneObject current: cards.keySet()) {
            if (cards.get(current) == null) {

                Vector position = Vector.sum(current.getPosition(), Resources.getMetrics().getVerticalIndent());

                card.setLayer(-2);
                card.setParent(this);
                card.setPosition(position);
                card.isHiddenFace(false);

                cards.put(current, card);
                singlesCount--;
                return;
            }
        }
    }

    /**
     * Метод, возвращающий первое вхождение, удовлетворяющее условию.
     * @param predicate Условие.
     * @return Первое вхождение, удовлетворяющее условию.
     */
    @Override
    public CardSceneObject first(Predicate<CardSceneObject, Boolean> predicate) {
        for (CardSceneObject card: cards.keySet()) {
            if (predicate.func(card))
                return card;

            CardSceneObject temp = cards.get(card);
            if (temp != null && predicate.func(temp))
                return temp;
        }

        return null;
    }

    /**
     * Метод, возвращающий первую неотбитую карту.
     * @return Первая неотбитая карта.
     */
    @Override
    public CardSceneObject getSingle() {
        if (!hasSingle())
            return null;

        for (CardSceneObject card: cards.keySet())
            if (cards.get(card) == null)
                return card;

        return null;
    }

    /**
     * Метод, возвращающий все карты.
     * @return Коллекция всех карт.
     */
    @Override
    public Collection<CardSceneObject> getAll() {
        Collection<CardSceneObject> result = new HashSet<>(cards.keySet());
        Collection<CardSceneObject> values = new HashSet<>(cards.values());
        values.removeIf(Objects::isNull);
        result.addAll(values);
        return result;
    }

    /**
     * Метод, очищающий стол.
     */
    @Override
    public void clear() {

        for (CardSceneObject card: cards.keySet()) {
            card.setParent(null);
            CardSceneObject temp = cards.get(card);
            if (temp != null)
                temp.setParent(null);
        }

        cards.clear();
        singlesCount = 0;
    }

    /**
     * Метод, удаляющий все карты.
     */
    @Override
    public void removeAll() {
        cards.clear();
    }

    /**
     * Метод, возвращающий колличество пар плюс колличество неотбитых карт.
     * @return Колличество пар плюс колличество неотбитых карт.
     */
    @Override
    public int count() {
        return cards.size();
    }

    /**
     * Метод проверки наличия неотбитых карт.
     * @return Результат проверки.
     */
    @Override
    public boolean hasSingle() {
        return singlesCount > 0;
    }
}
