package ru.durak.Kostya.model.implementation;

import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.*;
import ru.durak.Kostya.model.abstraction.PlayerBuilder;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;

import java.util.ArrayList;
import java.util.List;

public class DurakGame implements Game<CardSceneObject> {

    private final int defaultCards;

    private final DeckBuilder deckBuilder;

    private final PlayerBuilder playersBuilder;

    private final SceneObject scene;

    private final TableSceneObject table;

    private DeckSceneObject deck;

    private List<PlayerSceneObject<CardSceneObject>> players;

    private int current;

    public DurakGame(SceneObject scene, DeckBuilder deckBuilder, PlayerBuilder playersBuilder, TableSceneObject table) {
        this.defaultCards = Resources.getMetrics().getCardsCount();
        this.scene = scene;
        this.deckBuilder = deckBuilder;
        this.playersBuilder = playersBuilder;
        this.table = table;
        current = 0;
    }

    public void start() {
        clear();

        createPlayers();
        createDeck();

        deck.shuffle();

        gameCycle();
    }

    protected void gameCycle() {
        distribute();
    }

    protected void clear() {
        if (players != null)
            for (PlayerSceneObject<CardSceneObject> player: players)
                player.setParent(null);
    }

    protected void createPlayers() {
        Vector[] positions = Resources.getMetrics().getPlayersPositions();
        List<PlayerSceneObject<CardSceneObject>> tempPlayers = playersBuilder.build(this);
        int playersCount = Math.min(positions.length, tempPlayers.size());
        players = new ArrayList<>(playersCount);
        for (int i = 0; i < playersCount; i++) {
            players.add(tempPlayers.get(i));
            players.get(i).setPosition(positions[i]);
            players.get(i).setParent(scene);
        }
    }

    protected void createDeck() {
        deck = deckBuilder.build();
        deck.setParent(scene);
        Vector sceneSize = Resources.getMetrics().getSceneSize();
        Vector cardSize = Resources.getMetrics().getCardSize();
        double verticalIndent = cardSize.getY() - cardSize.getX() + Resources.getMetrics().getHorizontalIndent().getX();
        deck.setPosition(new Vector(sceneSize.getX() / 2 - cardSize.getY() / 2, verticalIndent));
    }

    protected void distribute() {
        for (int i = 0; i < defaultCards; i++)
            for (PlayerSceneObject<CardSceneObject> player : players) {
                if (deck.count() == 0)
                    return;

                if (player.count() == defaultCards)
                    continue;

                player.add(deck.pop());
            }
    }
}
