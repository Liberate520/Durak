package ru.durak.Kostya;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.durak.Kostya.infrastructure.*;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.builders.DeckBuilder;
import ru.durak.Kostya.model.abstraction.TableSceneObject;
import ru.durak.Kostya.model.abstraction.game.enums.Suit;
import ru.durak.Kostya.model.implementation.*;
import ru.durak.Kostya.model.implementation.base.TexturedGameObject;
import ru.durak.Kostya.model.implementation.builders.CardDeckBuilder;
import ru.durak.Kostya.model.implementation.builders.PlayerSceneObjectBuilder;

import java.util.*;

public class Durak extends Application {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args)  {
        Application.launch(args);
    }

    /**
     * Метод сборки приложения.
     */
    @Override
    public void start(Stage stage) {
        Resources.setTextures(new Textures());
        loadTextures();
        Image back = Resources.getTextures().getTexture("back");
        Vector size = new Vector(700, 400);
        Resources.setMetrics(new Metrics(
                6,
                new Vector(back.getWidth(), back.getHeight()),
                new Transform[] {
                        new Transform(new Vector(350, 290), 0),
                        new Transform(new Vector(80, 50), 125),
                        new Transform(new Vector(610, 50), 235)
                },
                size));

        TexturedGameObject gameScene = new GameScene(size);
        gameScene.setTexture(Resources.getTextures().getTexture("table"));

        DeckBuilder deckBuilder = new CardDeckBuilder(back, 6, 14, Suit.values());
        PlayerSceneObjectBuilder playersBuilder = new PlayerSceneObjectBuilder(1, 2);

        TableSceneObject<CardSceneObject> table = new TableGameObject();
        table.setPosition(Vector.div(size, 2));
        table.setParent(gameScene);

        DurakGame game = new DurakGame(gameScene, deckBuilder, playersBuilder, table);
        game.start();

        Pane pane = new Pane();
        pane.getChildren().addAll(gameScene.getGroup());
        Scene scene = new Scene(pane, size.getX(), size.getY());
        stage.setScene(scene);

        stage.show();
    }

    /**
     * Метод загрузки текстур.
     */
    private void loadTextures() {
        Image table = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/table.jpg")));
        Resources.getTextures().addTexture("table", table);

        Image back = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/deck/back.png")));
        Resources.getTextures().addTexture("back", back);

        for (Suit suit: Suit.values())
            for (int i = 6; i <= 14; i++) {
                Image face = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/deck/" + suit + "/" + i + ".png")));
                Resources.getTextures().addTexture(suit.toString() + i, face);
            }
    }
}