package org.example.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Level {
    EASY(10,8){
        {
            Collections.addAll(letters,"x","y","z");
        }

    },
    MEDIUM(8,6){
        {
            Collections.addAll(letters,"x","y","z","v");
        }
    },
    HARD(6,5){
        {
            Collections.addAll(letters,"x","y","z","v","t");
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
    public String getLetter(){
        Random random = new Random();
        int randomIndex = random.nextInt(letters.size());
        return letters.get(randomIndex);
    }
}
