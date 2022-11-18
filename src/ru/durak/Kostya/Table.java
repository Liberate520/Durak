package ru.durak.Kostya;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Table {
    private Desk desk = new Desk();
    List<Karta> koloda = new ArrayList<>();
    List<Karta> stol = new ArrayList<>();
    Group attack = new Group();
    Group defence = new Group();
    private Random rnd = new Random();


     Karta give(){
        if (koloda.size()!=0){
            if (koloda.size()==1){
                Karta karta = koloda.get(0);
                karta.rubaha().setTranslateY(0);
                koloda.remove(0);
                return karta;
            }
            else {
                int i = rnd.nextInt(koloda.size() - 1);
                Karta karta = koloda.get(i);
                karta.rubaha();
                koloda.remove(i);
                return karta;
            }
        }
        return null;
    }

    void newDesk(){
         desk.newDesk();
         koloda = desk.koloda;
    }
}
