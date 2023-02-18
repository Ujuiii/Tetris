package org.example.game;

import java.util.*;

public class Board {
    private Figure[][] board;
    private Figure[] inactiveFigures;
    private List<Figure> activeFigures = new ArrayList<>();
    private Level level;
    private int round = 0;
    private int score = 0;
    private Figure currentFigure;
    //  private List<Figure> nextFigures = new ArrayList<>();
    private Figure nextFigure;
    private int countMove;

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public Board() {
        //Вибір складності
        System.out.println("\nChoose level:\n1 - Easy\n2 - Medium\n3 - Hard");
        int scanLevel = scanner.nextInt();
        this.level = Level.EASY;

        if (scanLevel == 1)
            level = Level.EASY;
        if (scanLevel == 2)
            level = Level.MEDIUM;
        if (scanLevel == 3)
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
//        int randomIntFromInactiveArr= random.nextInt(inactiveFigures.length);
//        inactiveFigures[randomIntFromInactiveArr] = nextFigure;
//
//        nextFigure.setX(randomIntFromInactiveArr);
        createInactiveFig();

        currentFigure.setX(randomBoard0length);
        currentFigure.setY(0);

        menu();
    }

    private void menu() {

        if (round % 2 == 0 && round != 0 || activeFigures.isEmpty()) {
            activeFigures.add(nextFigure);
            board[0][nextFigure.getX()] = nextFigure; //додаємо в борд некст фігуру
            inactiveFigures[nextFigure.getX()] = null; //видаляємо з інактивних

            nextFigure = new Figure(level.getLetter());
            createInactiveFig();
        }
        print();
        check();
        move();

        check();
        for (Figure figure : activeFigures) {
            int prevPos = figure.getY();
            figure.setY(prevPos + 1);
            board[figure.getY()][figure.getX()] = figure;
            board[prevPos][figure.getX()] = null;
        }

        for (int x=0; x<board.length;x++){
            scoreRow(x);
        }
        for (int x=0; x<board[0].length;x++){
            scoreVert(x);
        }

        round++;

        menu();
    }

    private void createInactiveFig() {
        int randomIntFromInactiveArr = random.nextInt(inactiveFigures.length);
        inactiveFigures[randomIntFromInactiveArr] = nextFigure;
        nextFigure.setX(randomIntFromInactiveArr);
    }

    public void move() {
        try {
            System.out.println("\nChoose your move:\n1. left\n2. right\n3. bottom\nYOUR SCORE:"+score+"\nRound:"+round);
            int chooseMove = scanner.nextInt();
            int previousY = activeFigures.get(0).getY();
            int previousX = activeFigures.get(0).getX();
            if (chooseMove == 1) {
                activeFigures.get(0).setX(activeFigures.get(0).getX() - 1); //зсунули вліво на 1 по борду активну фігуру в листі з поз 0
                board[activeFigures.get(0).getY()][activeFigures.get(0).getX()] = activeFigures.get(0);
                board[previousY][previousX] = null;
            }
            if (chooseMove == 2) {
                activeFigures.get(0).setX(activeFigures.get(0).getX() + 1); //зсунули вліво на 1 по борду активну фігуру в листі з поз 0
                board[activeFigures.get(0).getY()][activeFigures.get(0).getX()] = activeFigures.get(0);
                board[previousY][previousX] = null;
            }
            if (chooseMove == 3) {

                activeFigures.get(0).setY(activeFigures.get(0).getY() + 1); //зсунули вниз на 1 по борду активну фігуру в листі з поз 0
                board[activeFigures.get(0).getY()][activeFigures.get(0).getX()] = activeFigures.get(0);
                board[previousY][activeFigures.get(0).getX()] = null;
            }
        } catch (ArrayIndexOutOfBoundsException b) {
        } catch (IndexOutOfBoundsException a) {
        }
    }

    //Видаляє з активних фігур коли заходить в "фіксовану зону"
    private void check() {
        try {

            if (activeFigures.get(0).getY() == board.length - 1
                    || board[activeFigures.get(0).getY() + 1][activeFigures.get(0).getX()] != null) {
                activeFigures.remove(0);
            }

        } catch (ArrayIndexOutOfBoundsException b) {
        } catch (IndexOutOfBoundsException b) {
        }


    }
    public void scoreVert(int vert){
        for (int y=0;y< board.length;y++){
            try {
                if (board[y][vert]!=null && board[y][vert].getSymbol().equals(board[y-1][vert].getSymbol()) && board[y][vert].getSymbol().equals(board[y-2][vert].getSymbol())
                        && board[y][vert].getSymbol().equals(board[y+1][vert].getSymbol()) && board[y][vert].getSymbol().equals(board[y+2][vert].getSymbol()) ) {
                    board[y][vert] = null;
                    board[y-1][vert] = null;
                    board[y-2][vert] = null;
                    board[y+1][vert] = null;
                    board[y+2][vert] = null;
                    this.score += 4;
                }


            } catch (ArrayIndexOutOfBoundsException | NullPointerException a) {
            }
            try {
                if (board[y][vert]!=null && board[y][vert].getSymbol().equals(board[y-1][vert].getSymbol()) && board[y][vert].getSymbol().equals(board[y-2][vert].getSymbol())) {
                    board[y][vert] = null;
                    board[y-1][vert] = null;
                    board[y-2][vert] = null;
                    this.score++;
                }
            } catch (ArrayIndexOutOfBoundsException | NullPointerException b) {
            }
        }
    }

    //Видаляє 3,5 попідряд горизонтальні однакові фігури, та додає рахунок гравцю
    public void scoreRow(int row) {
//        if ( x == board.length-1 && y == board[x].length-1){
//            return;
//        }


        for (int y = 0; y < board[row].length; y++) {
            try {
                if (board[row][y]!=null && board[row][y].getSymbol().equals(board[row][y - 1].getSymbol()) && board[row][y].getSymbol().equals(board[row][y - 2].getSymbol())
                        && board[row][y].getSymbol().equals(board[row][y + 1].getSymbol()) && board[row][y].getSymbol().equals(board[row][y + 2].getSymbol()) ) {
                    board[row][y] = null;
                    board[row][y - 1] = null;
                    board[row][y - 2] = null;
                    board[row][y + 1] = null;
                    board[row][y + 2] = null;
                    this.score += 4;
                }


            } catch (ArrayIndexOutOfBoundsException | NullPointerException a) {
            }
            try {
                if (board[row][y]!=null && board[row][y].getSymbol().equals(board[row][y - 1].getSymbol())  && board[row][y].getSymbol().equals(board[row][y - 2].getSymbol())) {
                    board[row][y] = null;
                    board[row][y - 1] = null;
                    board[row][y - 2] = null;
                    this.score++;
                }
            } catch (ArrayIndexOutOfBoundsException | NullPointerException b) {
            }
        }
    }


    public void print() {
        for (int x = 0; x < inactiveFigures.length; x++) {
            System.out.print(" ");
            System.out.print(inactiveFigures[x] == null ? "   " : inactiveFigures[x]);

        }
        System.out.println(" ");
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                System.out.print("|");
                System.out.print(board[x][y] == null ? "   " : board[x][y]);
            }
            System.out.println("|");

        }
        //тут ідея в тому,що або 3 іфа, або якось об'єднати в один і циклом друкувати цифри під комірками
        for (int x = 0; x < level.getWidth(); x++) {
            System.out.print("  " + (x + 1) + " ");
        }

    }
}
