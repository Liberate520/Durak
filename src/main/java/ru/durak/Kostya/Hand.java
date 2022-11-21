package ru.durak.Kostya;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

class Hand {
    List<Karta> hand = new ArrayList<>();
    Group group = new Group();
    Karta karta = new Karta(0,0);
    boolean bito = false;

    void dobor(Table koloda){
        while (6-hand.size() > 0){
            if (koloda.koloda.size() != 0) {
                Karta karta = koloda.give();
                hand.add(karta);
            }
            else break;
        }
        hand.sort(karta);
        group.getChildren().clear();
        for (Karta karta : hand){
            group.getChildren().add(karta.getImg());
        }
        razlozhitKartyRuki();
    }

    void razlozhitKartyRuki(){
        for (int i = 0; i < hand.size(); i++){
            group.getChildren().get(i).setTranslateX(20 * i);
        }
    }

    void vizible(){
        for (Karta karta:hand){
            karta.img();
        }
    }

    void invizible(){
        for (Karta karta:hand){
            karta.rubaha();
        }
    }

    Karta min () {
        Karta kartaMin = new Karta(0, 15);
        boolean bool = true;
        for (Karta karta : hand) {
            if (karta.getMast() != Durak.koz.getMast()) {
                if (karta.getZnah() < kartaMin.getZnah()) {
                    kartaMin = karta;
                    bool = false;
                }
            }
        }
        if (bool){
            for (Karta karta : hand){
                if (karta.getZnah() < kartaMin.getZnah()) {
                    kartaMin = karta;
                }
            }
        }
        return kartaMin;
    }

    Karta min (Karta karta1, boolean perevod){
        Karta kartaMin = new Karta(0,15);
        boolean bool = false;
        if (perevod) {
            for (Karta karta : hand) {
                if (karta.getZnah() == karta1.getZnah()) {
                    kartaMin = karta;
                    bool = true;
                }
            }
            if (bool) {
                return kartaMin;
            }
        }
        for (Karta karta : hand) {
            if (karta.getMast() == karta1.getMast()) {
                if (karta.getZnah() > karta1.getZnah() && karta.getZnah() < kartaMin.getZnah()) {
                    kartaMin = karta;
                    bool = true;
                }
            }
        }
        if (bool){
            return kartaMin;
        }
        if (!bool && karta1.getMast() != Durak.koz.getMast()){
            for (Karta karta : hand){
                if (karta.getZnah() < kartaMin.getZnah() && karta.getMast() == Durak.koz.getMast()) {
                    kartaMin = karta;
                    bool = true;
                }
            }
            if (bool){
                return kartaMin;
            }

        }
        return null;
    }

    Karta max () {
        Karta kartaMax = new Karta(0, 0);
        boolean bool = false;
        for (Karta karta : hand) {
            if (karta.getMast() == Durak.koz.getMast()) {
                if (karta.getZnah() > kartaMax.getZnah()) {
                    kartaMax = karta;
                    bool = true;
                }
            }
        }
        if (!bool){
            for (Karta karta : hand){
                if (karta.getZnah() > kartaMax.getZnah()) {
                    kartaMax = karta;
                }
            }
        }
        return kartaMax;
    }

    boolean koz(Hand hand){
        for (Karta karta : hand.hand){
            if (karta.getMast()==Durak.koz.getMast()){
                return true;
            }
        }
        return false;
    }

    boolean proverka(){
        return hand.size() == 0;
    }
}
