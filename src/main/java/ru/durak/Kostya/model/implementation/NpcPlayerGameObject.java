package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.PlayerSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.abstraction.game.Hand;
import ru.durak.Kostya.model.abstraction.game.enums.Suit;
import ru.durak.Kostya.model.implementation.base.PlayerGameObject;

/**
 * Класс, описывающий игрока, управляемого компьютером.
 */
public class NpcPlayerGameObject extends PlayerGameObject {

    /**
     * Инициализация игрока, управляемого компьютером.
     * @param cards Хранилище карт.
     * @param game Ссылка на игру.
     */
    public NpcPlayerGameObject(Hand<CardSceneObject> cards, Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> game) {
        super(cards, game);
    }

    /**
     * Переопределение метода добавления карты игроку.
     * @param newCard Добавляемая карта.
     */
    @Override
    public void add(CardSceneObject newCard) {
        super.add(newCard);
        if (newCard != null)
            newCard.isHiddenFace(true);
    }

    /**
     * Переопределение метода передачи хода игроку.
     */
    @Override
    public void getMove() {
        if (game.isAttack()) {
            if (game.getCount() == 0) {
                game.move(this, cards.first());
            } else {
                for (CardSceneObject card: game.getAll()) {
                    CardSceneObject findCard = cards.first(c -> c.getRank() == card.getRank());
                    if (findCard != null) {
                        game.move(this, findCard);
                        return;
                    }
                }
                game.skip(this);
            }
        } else {
            Suit trump = game.getTrump();
            CardSceneObject single = game.getSingle();
            CardSceneObject result = cards.first(c -> (c.getRank() > single.getRank() && c.getSuit() == single.getSuit()) ||
                    (c.getSuit() == trump && single.getSuit() != trump));

            if (result != null)
                game.move(this, result);
            else
                game.give(this);
        }
    }
}
