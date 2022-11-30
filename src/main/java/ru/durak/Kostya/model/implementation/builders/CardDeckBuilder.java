package ru.durak.Kostya.model.implementation.builders;

import javafx.scene.image.Image;
import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.builders.DeckBuilder;
import ru.durak.Kostya.model.abstraction.DeckSceneObject;
import ru.durak.Kostya.model.abstraction.game.enums.Suit;
import ru.durak.Kostya.model.implementation.CardGameObject;
import ru.durak.Kostya.model.implementation.DeckGameObject;

/**
 * Класс, описывающий фабрику по созданию колоды карт.
 */
public class CardDeckBuilder implements DeckBuilder {

    /**
     * Текстура рубашки карты.
     */
    private final Image back;

    /**
     * Значения минимального и максимального рангов карты.
     */
    private final int minRank, maxRank;

    /**
     * Массив мастей.
     */
    private final Suit[] suits;

    /**
     * Инициализация объекта фабрики.
     * @param back Текстура рубашки карты.
     * @param minRank Минимальный ранг.
     * @param maxRank Максимальный ранг.
     * @param suits Массив мастей.
     */
    public CardDeckBuilder(Image back, int minRank, int maxRank, Suit... suits) {
        this.back = back;
        this.minRank = minRank;
        this.maxRank = maxRank;
        this.suits = suits == null ? new Suit[0] : suits;
    }

    /**
     * Метод, возвращающий колоду карт.
     * @return Созданная колода карт.
     */
    @Override
    public DeckSceneObject build() {
        DeckSceneObject deck = new DeckGameObject();
        int layer = suits.length * (maxRank - minRank);
        for (Suit suit: suits)
            for (int i = minRank; i <= maxRank; i++) {
                CardSceneObject card = new CardGameObject(i, suit, back);
                card.setTexture(Resources.getTextures().getTexture(suit.toString() + i));
                card.isHiddenFace(true);
                card.setLayer(-layer++);
                deck.push(card);
            }

        return deck;
    }
}
