package ru.durak.Kostya;

import java.util.ArrayList;
import java.util.List;

public class Desk {
    List<Karta> koloda = new ArrayList<>();

    void newDesk(){
        koloda.clear();
        for (int i = 0; i < 4; i++){
            for (int k = 6; k <= 14; k++ ){
                Karta karta  = new Karta(i,k);
                karta.rubaha();
                koloda.add(karta);
            }
        }
    }
}
