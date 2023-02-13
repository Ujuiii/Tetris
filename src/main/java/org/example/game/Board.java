package org.example.game;

import java.util.Arrays;

public class Board {
    private Figure[][] board;


    public Board(Level level){
        board = new Figure[level.getHigh()][level.getWidth()];
    }
    public void print(){
        for (int x=0;x<board.length;x++){
            for (int y=0;y<board[x].length;y++){
                System.out.print("|");
                System.out.print(board[x][y]==null?" _ ":board[x][y]);
            }
            System.out.println("|");

        }
        //тут ідея в тому,що або 3 іфа, або якось об'єднати в один і циклом друкувати цифри під комірками
        for (int x=0;x<Level.HARD.getWidth();x++) {
            System.out.print("  "+(x+1)+" ");
        }

    }
}
