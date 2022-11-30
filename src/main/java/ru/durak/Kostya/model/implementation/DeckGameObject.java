package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.DeckSceneObject;
import ru.durak.Kostya.model.implementation.base.GameObject;

import java.util.Collections;
import java.util.Stack;

/**
 * Класс, описывающий колоду карт.
 */
public class DeckGameObject extends GameObject implements DeckSceneObject {

    /**
     * Стэк, хранящий объкты карт.
     */
    private final Stack<CardSceneObject> stack;

    /**
     * Инициализация объекта колоды карт.
     */
    public DeckGameObject() {
        stack = new Stack<>();
    }

    /**
     * Метод, добавляющий карты на верх колоды.
     * @param card Карта для добавления.
     */
    @Override
    public void push(CardSceneObject card) {
        if (card == null)
            return;

        card.setParent(this);
        card.setPosition(Vector.zero());
        card.isHiddenFace(true);
        stack.push(card);
    }

    /**
     * Метод, возвращающий верхнюю карту колоды и удаляющий ее из колоды.
     * @return Верхняя карта колоды.
     */
    @Override
    public CardSceneObject pop() {
        if (stack.size() == 0)
            return null;

        stack.peek().setParent(null);
        return stack.pop();
    }

    /**
     * Метод, возвращающий нижнюю карту колоды.
     * @return Нижняя карта колоды.
     */
    @Override
    public CardSceneObject peekLast() {
        return stack.size() > 0 ? stack.firstElement() : null;
    }

    /**
     * Метод, возвращающий колличество карт в колоде.
     * @return Колличество карт в колоде.
     */
    @Override
    public int count() {
        return stack.size();
    }

    /**
     * Метод, перемешивающий карты в колоде.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(stack);
        int i = 1;
        for (CardSceneObject card: stack)
            card.setLayer(-i++);
    }
}
