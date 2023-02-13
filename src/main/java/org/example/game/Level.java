package org.example.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Level {
    EASY(4,5){
        {
            Collections.addAll(letters,"x","y");
        }

    },
    MEDIUM(6,6){
        {
            Collections.addAll(letters,"x","y","z");
        }
    },
    HARD(8,8){
        {
            Collections.addAll(letters,"x","y","z","v");
        }
    };
    private int high;
    private int width;

    List<String> letters = new ArrayList<>();

    Level(int high,int width){
        this.high = high;
        this.width = width;
    }

    public int getHigh(){
        return high;
    }
    public int getWidth(){
        return width;
    }

    public List<String> getLetters(){
        return letters;
    }
    //ЗРОБИТИ МЕТОД для того щоб отримати рандомний символ в залежності від складності

}
