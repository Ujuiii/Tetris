package org.example.game;

import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        User bestPlayer = StorageController.findMaxScore();
        System.out.println("\nWelcome to tetris game. \nMax resultL:" +(bestPlayer==null?"The best player has not played yet"
                :bestPlayer.getName()+" "+bestPlayer.getScore()+" Points"));
        int menuChoose = InputController.readInt("\n1. New game \n2. Records \n3. Quit");
        if (menuChoose == 1) {
            GameController gameController = new GameController();
        }
        if (menuChoose == 2) {
            records();
        }
        if (menuChoose == 3){
            InputController.close();
            System.exit(0);
        }
        mainMenu();

    }
    private static void records(){
        String choiceLvl =InputController.readString("Type level(Easy,Medium,Hard) to see high records on this difficulty");
        try {
            Level level = Level.valueOf(choiceLvl.toUpperCase());
            //лист з даними про юзерів та їх результатів
            List<User> users = StorageController.find(level);
            for (User user : users) {
                System.out.println(user);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong input");
            mainMenu();
        }
    }

}
