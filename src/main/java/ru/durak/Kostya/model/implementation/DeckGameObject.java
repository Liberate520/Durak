package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.DeckSceneObject;

import java.util.Collections;
import java.util.Stack;

public class DeckGameObject extends GameObject implements DeckSceneObject {
    private final Stack<CardSceneObject> stack;

    private CardSceneObject last;

    public DeckGameObject() {
        stack = new Stack<>();
    }

    @Override
    public void push(CardSceneObject card) {
        if (card == null)
            return;

        if (stack.size() == 0)
            last = card;

        card.setParent(this);
        card.setPosition(Vector.zero());
        card.isHiddenFace(true);
        stack.push(card);
    }

    @Override
    public CardSceneObject pop() {
        if (stack.size() == 0)
            return null;

        if (stack.size() == 1)
            last = null;

        stack.peek().setParent(null);
        return stack.pop();
    }

    @Override
    public CardSceneObject peekLast() {
        return last;
    }

    @Override
    public int count() {
        return stack.size();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(stack);
    }
}