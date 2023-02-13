package org.example.game;

import java.util.*;

public class Board {
    private Figure[][] board;
    private List<Figure> activeFigures = new ArrayList<>();
    private Level level;
    private int round = 0;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    public Board(){
        //Вибір складності
        System.out.println("Choose level: 1 - Easy\n 2 - Medium\n 3 - Hard");
        int scanLevel = scanner.nextInt();
        this.level = Level.EASY;

        if (scanLevel==1)
            level = Level.EASY;
        if (scanLevel==2)
            level = Level.MEDIUM;
        if (scanLevel==3)
            level = Level.HARD;
        board = new Figure[level.getHigh()][level.getWidth()];
        //Тут будуть правила гри
        System.out.println("");
        //тут рандом який генерить першу фігуру на борді
        int pos = random.nextInt(board[0].length);
        Figure figure = Level // Level.ЯКИЙСЬМЕТОД З ЛЕВЕЛУ ЯКИЙ ГЕНЕРУЄ Рандомний символ
        menu();
    }
    private void menu(){
        print();

        if (round%2==0){

        }



        System.out.println("1. left\n 2. right\n 3. bottom\n 4. back to menu");
        int chooseMove = scanner.nextInt();
        if (chooseMove==1){}
        if (chooseMove==2){}
        if (chooseMove==3){}
        if (chooseMove==4){return;}

        menu();
    }
    public void print(){
        for (int x=0;x<board.length;x++){
            for (int y=0;y<board[x].length;y++){
                System.out.print("|");
                System.out.print(board[x][y]==null?"   ":board[x][y]);
            }
            System.out.println("|");

        }
        //тут ідея в тому,що або 3 іфа, або якось об'єднати в один і циклом друкувати цифри під комірками
        for (int x=0;x<level.getWidth();x++) {
            System.out.print("  "+(x+1)+" ");
        }

    }
}
