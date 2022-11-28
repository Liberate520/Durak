package ru.durak.Kostya;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.durak.Kostya.infrastructure.Metrics;
import ru.durak.Kostya.infrastructure.Resources;
import ru.durak.Kostya.infrastructure.Textures;
import ru.durak.Kostya.infrastructure.Vector;
import ru.durak.Kostya.model.abstraction.CardSceneObject;
import ru.durak.Kostya.model.abstraction.builders.DeckBuilder;
import ru.durak.Kostya.model.abstraction.TableSceneObject;
import ru.durak.Kostya.model.abstraction.game.enums.Suit;
import ru.durak.Kostya.model.abstraction.scene.SceneObject;
import ru.durak.Kostya.model.implementation.*;
import ru.durak.Kostya.model.implementation.base.TexturedGameObject;
import ru.durak.Kostya.model.implementation.builders.CardDeckBuilder;
import ru.durak.Kostya.model.implementation.builders.PlayerSceneObjectBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Durak extends Application {

    private int hod;
    private Table table = new Table();
    static Karta koz;
    private static List<Hand> player = new ArrayList<>();
    private boolean activ = false;
    private int defer = 4;
    boolean perevod = true;
    Karta stopka = new Karta(0, 7);


    public static void main(String[] args)  {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        //oldVersion(stage);
        newVersion(stage);
    }

    public void newVersion(Stage stage) {
        Resources.setTextures(new Textures());
        loadImages();
        Image back = Resources.getTextures().getTexture("back");
        Vector size = new Vector(700, 400);
        Resources.setMetrics(new Metrics(
                6,
                new Vector(back.getWidth(), back.getHeight()),
                new Vector[] {
                        new Vector(350, 290),
                        new Vector(-10, 50),
                        new Vector(530, 50)
                },
                size));

        TexturedGameObject gameScene = new GameScene(size);
        gameScene.setTexture(Resources.getTextures().getTexture("table"));

        DeckBuilder deckBuilder = new CardDeckBuilder(back, 6, 14, Suit.values());
        PlayerSceneObjectBuilder playersBuilder = new PlayerSceneObjectBuilder(3, 0);

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

    private void loadImages() {
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


    public void oldVersion(Stage stage) {

        Image tableImage = new Image(getClass().getResourceAsStream("image/table.jpg"));
        ImageView img = new ImageView(tableImage);
        img.setX(20);
        img.setY(10);
        Button beru = new Button("Беру!");
        Button bito = new Button("Бито!");
        beru.setTranslateX(180);
        beru.setTranslateY(290);
        bito.setTranslateX(180);
        bito.setTranslateY(320);
        beru.setOnAction(actionEvent -> {
            if (activ && defer == 0){
                beru(0);
            }
        });
        bito.setOnAction(actionEvent -> {
            if (activ && defer != 0 && table.stol.size() != 0){
                player.get(0).bito = true;
                for (int j = 0; j < 3; j++) {
                    if (!player.get(j).bito && j != defer) {
                        podbrosit(j);
                        return;
                    }
                }
                bito();
            }
        }
        );

        Pane pane = new Pane();
        pane.getChildren().addAll(img);
        Scene scene = new Scene(pane, 700, 400);
        stage.setScene(scene);
        stage.show();

        player.add(new Hand());
        player.add(new Hand());
        player.add(new Hand());

        table.newDesk();
        koz = table.give();
        table.koloda.add(koz);

        player.get(0).group.setTranslateX(250);
        player.get(0).group.setTranslateY(290);
        player.get(1).group.setRotate(125);
        player.get(1).group.setTranslateX(-10);
        player.get(1).group.setTranslateY(50);
        player.get(2).group.setRotate(235);
        player.get(2).group.setTranslateX(530);
        player.get(2).group.setTranslateY(50);
        koz.img().setTranslateX(310);
        koz.img().setTranslateY(30);
        Karta kozShadow = new Karta(koz.getMast(), koz.getZnah());
        kozShadow.img().setTranslateX(310);
        kozShadow.getImg().setTranslateY(30);
        kozShadow.getImg().setOpacity(0.5);
        stopka.rubaha().setTranslateX(310);
        stopka.rubaha().setTranslateY(5);
        stopka.rubaha().setRotate(90);
        stopka.rubaha().setScaleY(1.2);
        table.attack.setTranslateX(140);
        table.attack.setTranslateY(145);
        table.defence.setTranslateX(140);
        table.defence.setTranslateY(180);

        pane.getChildren().addAll(player.get(0).group, player.get(1).group, player.get(2).group,
                kozShadow.getImg(), koz.img(), stopka.rubaha(), table.attack, table.defence, beru, bito);

        newGame();
    }


    void newGame (){
        dobor();
        cheyHod();
        defer();
        kon();
    }

    private void dobor(){
        player.get(0).dobor(table);
        player.get(1).dobor(table);
        player.get(2).dobor(table);
        player.get(0).vizible();
        if (table.koloda.size() < 2){
            stopka.rubaha().setOpacity(0);
        }
        anim();
    }

    private void cheyHod(){
        if (bolshe(player.get(0).max(), player.get(1).max())){
            if (bolshe(player.get(0).max(), player.get(2).max())){
                hod = 0;
            }
            else {
                hod = 2;
            }
        }
        else {
            if (bolshe(player.get(1).max(), player.get(2).max())){
                hod = 1;
            }
            else {
                hod = 2;
            }
        }
    }

    private boolean bolshe(Karta karta1, Karta karta2){
        if (karta1.getMast() == koz.getMast() && karta2.getMast() != koz.getMast()){
            return true;
        }
        else if (karta1.getMast() != koz.getMast() && karta2.getMast() == koz.getMast()){
            return false;
        }
        else {
            return karta1.getZnah() > karta2.getZnah();
        }
    }

    private boolean moguOtbit(Karta karta1, Karta karta2){
        if (karta1.getMast() == koz.getMast() && karta2.getMast() != koz.getMast()){
            return true;
        }
        else return karta1.getMast() == karta2.getMast() && karta1.getZnah() > karta2.getZnah();
    }

    private boolean gameOver(){
        int a = 0;
        for (Hand hand : player) {
            if (hand.proverka()) {
                a++;
            }
        }
        return a == player.size() - 1 && table.koloda.size() == 0;
    }

    private void kon() {
        if (gameOver()){
            for (int i = 0; i < player.size(); i++ ){
                if (player.get(i).hand.size() != 0){
                    switch (i) {
                        case 0:
                            System.out.println("Вы проиграли");
                            break;
                        case 1:
                            System.out.println("Проиграл игрок слева");
                            break;
                        case 2:
                            System.out.println("Проиграл игрок справа");
                            break;
                    }
                    return;
                }
            }
            System.out.println("Ничья");
            return;
        }
        if (player.get(hod).hand.size() == 0){
            hod = hod + 1 - 3*((hod + 1)/3);
            kon();
            return;
        }
        perevod = true;

        if (hod != 0) {  // здесь игрок делает первый ход
            hod(player.get(hod).min());
            defer();
            defence();
        } else {
            activ = true;
        }
    }

    private void deferPlus(){
        defer = defer + 1 - 3 * ((defer + 1) / 3);
        if (player.get(defer).hand.size() == 0){
            deferPlus();
        }
    }

    private void defer(){
        defer = hod + 1 - 3 * ((hod + 1) / 3);
        if (player.get(defer).hand.size() == 0){
            deferPlus();
        }
    }

    private void podbrosit(int i){
        if (table.attack.getChildren().size() == 6 || player.get(defer).hand.size() == 0){
            player.get(i).bito = true;
            System.out.println("Бито!");
            for (int j = 0; j < 3; j++) {
                if (!player.get(j).bito && j != defer) {
                    podbrosit(j);
                    return;
                }
            }
            bito();
        }
        if (i == 0 && player.get(0).hand.size() != 0){
            activ = true;
        }
        else {
            Karta kartaMin = new Karta(4, 15);
            for (Karta kartaTable : table.stol) {
                for (Karta karta : player.get(i).hand) {
                    if (table.koloda.size() < 5) {
                        if (karta.getZnah() == kartaTable.getZnah()) {
                            if (bolshe(kartaMin, karta)) {
                                kartaMin = karta;
                            }
                        }
                    } else if (karta.getZnah() == kartaTable.getZnah() && karta.getMast() != koz.getMast()) {
                        if (bolshe(kartaMin, karta)) {
                            kartaMin = karta;
                        }
                    }
                }
            }
            if (kartaMin.getMast() == 4) {
                player.get(i).bito = true;
                System.out.println("Бито!");
                for (int j = 0; j < 3; j++) {
                    if (!player.get(j).bito && j != defer) {
                        podbrosit(j);
                        return;
                    }
                }
                bito();
            } else {
                for (Hand hand : player) {
                    hand.bito = false;
                }
                table.stol.add(kartaMin);
                kartaMin.img().setTranslateX(70 * table.attack.getChildren().size());
                table.attack.getChildren().add(kartaMin.getImg());
                player.get(i).hand.remove(kartaMin);
                player.get(i).razlozhitKartyRuki();
                System.out.println("Подкидываю " + kartaMin);
                defence();
            }
        }
    }

    private void bito(){
        table.stol.clear();
        table.attack.getChildren().clear();
        table.defence.getChildren().clear();
        for (Hand hand : player){
            hand.bito = false;
        }
        dobor();
        hod = defer;
        kon();
    }

    private void defence(){
        if (defer == 0) {
            activ = true;
        } else if (table.defence.getChildren().size() == 0) {
            int b = table.attack.getChildren().size() - table.defence.getChildren().size();
            for (int i = 0; i < b; i++) {
                int a = defence(defer, table.stol.get(i));
                if (a == 0) {
                    return;
                } else if (a == 1) {
                    deferPlus();
                    defence();
                    return;
                } else {
                    perevod = false;
                }
            }
            if (defer != hod) {
                podbrosit(hod);
            } else {
                podbrosit(hod + 1 - 3 * ((hod + 1) / 3));
            }
        } else {
            int a = defence(defer, table.stol.get(table.stol.size() - 1));
            if (a == 0) {
            } else if (a == 1) {
                deferPlus();
                defence();
            } else {
                perevod = false;
                if (defer != hod) {
                    podbrosit(hod);
                } else {
                    podbrosit(hod + 1 - 3 * ((hod + 1) / 3));
                }
            }
        }
    }

    private int defence(int i, Karta karta){
        Karta karta1 = player.get(i).min(karta, perevod);
        if (karta1 == null){
            beru(i);
            return 0;
        }
        else if (karta1.getZnah() == karta.getZnah() && perevod){
            ImageView kartaImg = karta1.img();
            kartaImg.setTranslateX(70 * table.attack.getChildren().size());
            table.attack.getChildren().add(kartaImg);
            table.stol.add(karta1);
            player.get(i).hand.remove(karta1);
            player.get(i).group.getChildren().remove(kartaImg);
            player.get(i).razlozhitKartyRuki();
            return 1;
        }
        else {
            ImageView kartaImg = karta1.img();
            kartaImg.setTranslateX(70 * table.defence.getChildren().size());
            table.defence.getChildren().add(kartaImg);
            table.stol.add(karta1);
            player.get(i).hand.remove(karta1);
            player.get(i).group.getChildren().remove(kartaImg);
            player.get(i).razlozhitKartyRuki();
            return 2;
        }
    }

    private void beru(int i){
        System.out.println("Взял!");
        player.get(i).hand.addAll(table.stol);
        table.stol.clear();
        table.attack.getChildren().clear();
        table.defence.getChildren().clear();
        for (Hand hand : player){
            hand.bito = false;
        }
        if (i != 0){
            player.get(i).invizible();
        }
        dobor();
        hod = i + 1 - 3*((i+1)/3);
        kon();
    }

    void hod(Karta karta){
        player.get(hod).hand.remove(karta);
        ImageView kartaImg = karta.img();
        table.stol.add(karta);
        kartaImg.setTranslateX(70 * table.attack.getChildren().size());
        table.attack.getChildren().add(kartaImg);
        player.get(hod).razlozhitKartyRuki();
    }

    private void anim() {
        for (int i = 0; i < player.get(0).hand.size(); i++) {
            Karta karta = player.get(0).hand.get(i);
            ImageView kartaImg = karta.getImg();
            kartaImg.setOnMouseEntered(mouseEvent -> {
                if (kartaImg.getParent() == player.get(0).group && activ) {
                    kartaImg.setTranslateY(-10);
                }
            });
            kartaImg.setOnMouseExited(mouseEvent -> {
                if (kartaImg.getParent() == player.get(0).group && activ) {
                    kartaImg.setTranslateY(0);
                }
            });
            kartaImg.setOnMouseClicked(mouseEvent -> {
                if (activ) {
                    if (kartaImg.getParent() == player.get(0).group) {
                        if (table.stol.size() == 0){
                            kartaImg.setTranslateX(70 * table.attack.getChildren().size());
                            table.attack.getChildren().add(kartaImg);
                            table.stol.add(karta);
                            player.get(0).hand.remove(karta);
                            player.get(0).group.getChildren().remove(karta);
                            player.get(0).razlozhitKartyRuki();
                            activ = false;
                            defer();
                            defence();
                        }
                        else if (table.defence.getChildren().size() == 0 && karta.getZnah() == table.stol.get(0).getZnah()){
                            kartaImg.setTranslateX(70 * table.attack.getChildren().size());
                            table.attack.getChildren().add(kartaImg);
                            table.stol.add(karta);
                            player.get(0).hand.remove(karta);
                            player.get(0).group.getChildren().remove(karta);
                            player.get(0).razlozhitKartyRuki();
                            activ = false;
                            deferPlus();
                            defence();
                        }
                        else if (perevod && defer == 0 && moguOtbit(karta, table.stol.get(table.defence.getChildren().size()))){
                            kartaImg.setTranslateX(70 * table.defence.getChildren().size());
                            table.defence.getChildren().add(kartaImg);
                            table.stol.add(karta);
                            player.get(0).hand.remove(karta);
                            player.get(0).group.getChildren().remove(karta);
                            player.get(0).razlozhitKartyRuki();
                            activ = false;
                            if (table.defence.getChildren().size() != table.attack.getChildren().size()){
                                activ = true;
                                return;
                            }
                            perevod = false;
                            if (defer != hod){
                                podbrosit(hod);
                            }
                            else {podbrosit(hod + 1 -3 * ((hod + 1)/3));}
                        }
                        else if (defer == 0 && moguOtbit(karta, table.stol.get(table.stol.size() -1))){
                            kartaImg.setTranslateX(70 * table.defence.getChildren().size());
                            table.defence.getChildren().add(kartaImg);
                            table.stol.add(karta);
                            player.get(0).hand.remove(karta);
                            player.get(0).group.getChildren().remove(karta);
                            player.get(0).razlozhitKartyRuki();
                            activ = false;
                            if (table.defence.getChildren().size() != table.attack.getChildren().size()){
                                activ = true;
                                return;
                            }
                            perevod = false;
                            if (defer != hod){
                                podbrosit(hod);
                            }
                            else {podbrosit(hod + 1 -3 * ((hod + 1)/3));}
                        }
                        else if (table.attack.getChildren().size() == table.defence.getChildren().size()){
                            for (Karta karta1 : table.stol){
                                if (karta1.getZnah() == karta.getZnah()){
                                    player.get(0).hand.remove(karta);
                                    table.stol.add(karta);
                                    kartaImg.setTranslateX(70 * table.attack.getChildren().size());
                                    table.attack.getChildren().add(kartaImg);
                                    player.get(0).razlozhitKartyRuki();
                                    activ = false;
                                    defence();
                                    break;
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}