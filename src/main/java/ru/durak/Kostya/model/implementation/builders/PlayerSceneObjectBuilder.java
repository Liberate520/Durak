package ru.durak.Kostya.model.implementation.builders;

import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.builders.PlayerBuilder;
import ru.durak.Kostya.model.abstraction.PlayerSceneObject;
import ru.durak.Kostya.model.abstraction.game.Game;
import ru.durak.Kostya.model.implementation.CardHand;
import ru.durak.Kostya.model.implementation.ControlPlayerGameObject;
import ru.durak.Kostya.model.implementation.NpcPlayerGameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий фабрику по созданию игроков.
 */
public class PlayerSceneObjectBuilder implements PlayerBuilder {

    /**
     * Колличество игроков, управляемых человеком.
     */
    private final int controlPlayersCount;

    /**
     * Колличество игроков, управляемых компьютером.
     */
    private final int npcPlayersCount;

    /**
     * Инициализация объекта фабрики по созданию игроков.
     * @param controlPlayersCount Колличество игроков, управляемых человеком.
     * @param npcPlayersCount Колличество игроков, управляемых компьютером.
     */
    public PlayerSceneObjectBuilder(int controlPlayersCount, int npcPlayersCount) {

        if (controlPlayersCount + npcPlayersCount < 2) {
            this.controlPlayersCount = 1;
            this.npcPlayersCount = 1;
            return;
        }

        this.controlPlayersCount = Math.max(controlPlayersCount, 0);
        this.npcPlayersCount = Math.max(npcPlayersCount, 0);
    }

    /**
     * Метод, возвращающий список игроков.
     * @param game Ссылка на игру.
     * @return Список созданных игроков.
     */
    @Override
    public List<PlayerSceneObject<CardSceneObject>> build(Game<CardSceneObject, PlayerSceneObject<CardSceneObject>> game) {
        List<PlayerSceneObject<CardSceneObject>> players = new ArrayList<>(controlPlayersCount + npcPlayersCount);

        int i = 0;

        for (; i < controlPlayersCount; i++)
            players.add(new ControlPlayerGameObject(new CardHand<>(), game));

        for (; i < controlPlayersCount + npcPlayersCount; i++)
            players.add(new NpcPlayerGameObject(new CardHand<>(), game));

        return players;
    }
}
