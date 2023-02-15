package org.example.game;

import java.util.*;

public class Board {
    private Figure[][] board;
    private Figure[] inactiveFigures;
    private List<Figure> activeFigures = new ArrayList<>();
    private Level level;
    private int round = 1;
    private Figure currentFigure;
  //  private List<Figure> nextFigures = new ArrayList<>();
    private Figure nextFigure;

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    public Board(){
        //Вибір складності
        System.out.println("\nChoose level:\n1 - Easy\n2 - Medium\n3 - Hard");
        int scanLevel = scanner.nextInt();
        this.level = Level.EASY;

        if (scanLevel==1)
            level = Level.EASY;
        if (scanLevel==2)
            level = Level.MEDIUM;
        if (scanLevel==3)
            level = Level.HARD;
        board = new Figure[level.getHigh()][level.getWidth()];
        inactiveFigures = new Figure[level.getWidth()];

        //Тут будуть правила гри
        System.out.println("");
        //тут рандом який генерить першу фігуру на борді
        currentFigure = new Figure(level.getLetter()); // Level.ЯКИЙСЬМЕТОД З ЛЕВЕЛУ ЯКИЙ ГЕНЕРУЄ Рандомний символ
        activeFigures.add(currentFigure);

        int randomBoard0length = random.nextInt(board[0].length);
        board[0][randomBoard0length] = currentFigure;

        nextFigure = new Figure(level.getLetter());
        //nextFigure.add(nextFigure); //додаємо в 0 поз наступну фігуру
        int randomIntFromInactiveArr= random.nextInt(inactiveFigures.length);
        inactiveFigures[randomIntFromInactiveArr] = nextFigure;

        nextFigure.setX(randomIntFromInactiveArr);

        currentFigure.setX(randomBoard0length);
        currentFigure.setY(0);

        menu();
    }
    private void menu(){

        if (round%3==0){
            activeFigures.add(nextFigure);
            board[0][nextFigure.getX()] = nextFigure;
            inactiveFigures[nextFigure.getX()] = null;

            nextFigure = new Figure(level.getLetter());
            createInactiveFig();
        }
        print();


        System.out.println("\n1. left\n2. right\n3. bottom\n4. back to menu");
        int chooseMove = scanner.nextInt();
        int previousY=activeFigures.get(0).getY();
        if (chooseMove==1){
        }
        if (chooseMove==2){

        }
        if (chooseMove==3){

            activeFigures.get(0).setY(activeFigures.get(0).getY()+1); //зсунули вниз на 1 по борду активну фігуру в листі з поз 0
            board[activeFigures.get(0).getY()][activeFigures.get(0).getX()] = activeFigures.get(0);
            board[previousY][activeFigures.get(0).getX()] = null;
        }
        for (Figure figure:activeFigures){
            int prevPos =figure.getY();
            figure.setY(prevPos+1);
            board[figure.getY()][figure.getX()] = figure;
            board[prevPos][figure.getX()] = null;
        }
        if (chooseMove==4){return;}
        round++;

        menu();
    }
    private void createInactiveFig() {
        int randomIntFromInactiveArr= random.nextInt(inactiveFigures.length);
        inactiveFigures[randomIntFromInactiveArr] = nextFigure;
        nextFigure.setX(randomIntFromInactiveArr);
    }


    public void print(){
        for (int x=0;x<inactiveFigures.length;x++){
            System.out.print(inactiveFigures[x]==null?"   ":inactiveFigures[x]);

        }
        System.out.println();
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
