package org.example.game;

import java.util.Scanner;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        start();
    }

    private static void start() {

        System.out.println("Welcome to tetris game");
        System.out.println("1. New game \n 2. Records \n 3. Quit");
        int menuChoose = scanner.nextInt();
        if (menuChoose == 1) {
            Board board = new Board();
        }
        if (menuChoose == 2) {
        }
        if (menuChoose == 3){
            scanner.close();
            System.exit(0);
        }
        start();



    }

}
