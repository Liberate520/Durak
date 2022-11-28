package ru.durak.Kostya.model.implementation.builders;

import javafx.scene.image.Image;
import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.builders.DeckBuilder;
import ru.durak.Kostya.model.abstraction.DeckSceneObject;
import ru.durak.Kostya.model.abstraction.game.enums.Suit;
import ru.durak.Kostya.model.implementation.CardGameObject;
import ru.durak.Kostya.model.implementation.DeckGameObject;

public class CardDeckBuilder implements DeckBuilder {

    private final Image back;

    private final int minRank;

    private final int maxRank;

    private final Suit[] suits;

    public CardDeckBuilder(Image back, int minRank, int maxRank, Suit... suits) {
        this.back = back;
        this.minRank = minRank;
        this.maxRank = maxRank;
        this.suits = suits == null ? new Suit[0] : suits;
    }

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
