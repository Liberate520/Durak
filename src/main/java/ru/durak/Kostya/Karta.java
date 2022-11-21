package ru.durak.Kostya;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;


public class Karta implements Comparator<Karta> {
    private int mast;
    private ImageView img = new ImageView();
    private int znah;

    Karta(int mast, int znah) {
        this.mast = mast;
        this.znah = znah;
    }

    public ImageView getImg() {
        return img;
    }

    public int getMast() {
        return mast;
    }

    public int getZnah() {
        return znah;
    }

    ImageView img(){
        String z;
        switch (znah){
            case 11: z = "J"; break;
            case 12: z = "q"; break;
            case 13: z = "k"; break;
            case 14: z = "t"; break;
            default: z = String.valueOf(znah);
        }

        String m = "";
        switch (mast){
            case 0: m = "черви"; break;
            case 1: m = "пики"; break;
            case 2: m = "буби"; break;
            case 3: m = "крести"; break;
        }

        Image image = new Image(getClass().getResourceAsStream("image/колода/"+m+"/"+z+".png"));
        img.setImage(image);
        return img;
    }

    ImageView rubaha(){
        Image image = new Image(getClass().getResourceAsStream("image/колода/рубашка.png"));
        img.setImage(image);
        return img;
    }

    @Override
    public String toString() {
        return "Karta{" +
                "mast=" + mast +
                ", znah=" + znah +
                '}';
    }

    @Override
    public int compare(Karta karta1, Karta karta2){
        if (karta1.getMast() == Durak.koz.getMast() && karta2.getMast() != Durak.koz.getMast()){
            return -1;
        }
        else if (karta1.getMast() != Durak.koz.getMast() && karta2.getMast() == Durak.koz.getMast()){
            return 1;
        }
        else if (karta1.getMast() > karta2.getMast()){
            return 1;
        }
        else if (karta1.getMast() < karta2.getMast()){
            return -1;
        }
        else {
            return karta2.getZnah() - karta1.getZnah();
        }
    }
}
