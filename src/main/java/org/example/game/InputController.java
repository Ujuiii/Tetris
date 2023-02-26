package org.example.game;

import java.util.Scanner;

public class InputController {
    private static Scanner scanner = new Scanner(System.in);

    public static Integer readInt(String text){
        try {
            System.out.println(text);
            int value = Integer.parseInt(scanner.nextLine());
            return value;
        } catch (NumberFormatException e){
            System.out.println("Incorrect Value");
        }
        return readInt(text);
    }
    public static String readString(String text){
        System.out.println(text);
        return scanner.nextLine();
    }

    public static Level readLevel(String text){
        Level level = Level.EASY;
        try {
            System.out.println(text);
            int choice = readInt(text);
            if (choice == 1)
                level = Level.EASY;
            if (choice == 2)
                level = Level.MEDIUM;
            if (choice == 3)
                level = Level.HARD;

        } catch (NumberFormatException e){

        }
        return level;
    }
    public static void close(){
        scanner.close();
    }
}
