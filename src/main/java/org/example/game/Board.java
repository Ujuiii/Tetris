package org.example.game;

import java.util.*;

public class Board {
    private Figure[][] board;
    private Figure[] inactiveFigures;
    private List<Figure> activeFigures = new ArrayList<>();
    private Level level;
    private int round = 0;
    private String playerName;
    //Якщо тру виходить в головне меню
    private boolean isFinish;

    //Кожну n кількість дій додається фігура в лист активних фігур з неактивних фігур масива
    private int score = 0;
    //  private List<Figure> nextFigures = new ArrayList<>();
    private Figure nextFigure;
    private int countMove;

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public Board(Level level){
        board = new Figure[level.getHigh()][level.getWidth()];
        inactiveFigures = new Figure[level.getWidth()];

        //Тут будуть правила гри
        System.out.println("Rules: You have a figures and you have to move them to score points, " +
                " each move they move down by themselves. When figures placed in a row or in a column of N number of figures," +
                " they will disappear and add points to the player.");
        //тут рандом який генерить першу фігуру на борді
        Figure currentFigure = new Figure(level.getLetter()); // Level.ЯКИЙСЬМЕТОД З ЛЕВЕЛУ ЯКИЙ ГЕНЕРУЄ Рандомний символ
        activeFigures.add(currentFigure);
        int randomBoard0length = random.nextInt(board[0].length);
        board[0][randomBoard0length] = currentFigure;
        currentFigure.setX(randomBoard0length);
        currentFigure.setY(0);
        nextFigure = new Figure(level.getLetter());
        createInactiveFig();
    }
//    public Board() {
//
//        board = new Figure[level.getHigh()][level.getWidth()];
//        inactiveFigures = new Figure[level.getWidth()];
//
//        //Тут будуть правила гри
//        System.out.println("Rules: You have a figures and you have to move them to score points, " +
//                " each move they move down by themselves. When figures placed in a row or in a column of N number of figures," +
//                " they will disappear and add points to the player.");
//        //тут рандом який генерить першу фігуру на борді
//        Figure currentFigure = new Figure(level.getLetter()); // Level.ЯКИЙСЬМЕТОД З ЛЕВЕЛУ ЯКИЙ ГЕНЕРУЄ Рандомний символ
//        activeFigures.add(currentFigure);
//        int randomBoard0length = random.nextInt(board[0].length);
//        board[0][randomBoard0length] = currentFigure;
//        currentFigure.setX(randomBoard0length);
//        currentFigure.setY(0);
//        nextFigure = new Figure(level.getLetter());
//        createInactiveFig();
//        menu();
//    }
    public int getScore(){
        return score;
    }
    public int getRound(){
        return round;
    }
    public Level getLevel(){
        return level;
    }


    //all logic here
    private void menu() {
        deleteIfFix(activeFigures.get(0));

        move();
        if (isFinish) {
            return;
        }
//        for (Figure figure : activeFigures) {
//            int prevPos = figure.getY();
//            //перевіряє чи є в тому ж стовпці на 1 нижче фігура,
//                figure.setY(prevPos + 1);
//                board[figure.getY()][figure.getX()] = figure;
//                board[prevPos][figure.getX()] = null;
//        }
        for (int i=activeFigures.size()-1;i>=0;i--){
            Figure figure = activeFigures.get(i);
            int prevPos = figure.getY();
            //перевіряє чи є в тому ж стовпці на 1 нижче фігура,
                figure.setY(prevPos + 1);
                board[figure.getY()][figure.getX()] = figure;
                board[prevPos][figure.getX()] = null;

            deleteIfFix(figure);
        }




        if ((activeFigures.isEmpty() && round % 2 != 0) || round % 2 == 0 && round != 0) {
            addNextFig();
        }
        for (int x = 0; x < board.length; x++) {
            scoreRow(x);
        }
        for (int x = 0; x < board[0].length; x++) {
            scoreVert(x);
        }

        //Гра закінчилася якщо тру
        isFinish();
        menu();
    }

    //кожні 2 раунди додає фігуру з неактивного масивв в лист активних, або активний лист пустий теж додає
    public void addNextFig() {
            activeFigures.add(nextFigure); //додаємо з інактивного масива в активний лист
            board[0][nextFigure.getX()] = nextFigure; //додаємо в борд некст фігуру
            inactiveFigures[nextFigure.getX()] = null; //видаляємо з інактивних

            nextFigure = new Figure(level.getLetter());
            createInactiveFig();
    }

    public boolean isFinish(){
        for (int x=0; x<board.length;x++){
            for (int y=0; y<board[x].length;y++){
                if (board[x][y]==null){
                    isFinish=false;
                    return false;
                }
            }
        }
        isFinish=true;
        return true;
    }
    public void createInactiveFig() {
        int randomIntFromInactiveArr;
        do {
            randomIntFromInactiveArr = random.nextInt(inactiveFigures.length);
        } while (board[0][randomIntFromInactiveArr]!=null);
        inactiveFigures[randomIntFromInactiveArr] = nextFigure;
        nextFigure.setX(randomIntFromInactiveArr);
    }

    public void move(int direction) {
        try {
           // System.out.println("\nYOUR SCORE:" + score + "\nRound:" + round);
            int chooseMove = scanner.nextInt();
            int previousY = activeFigures.get(0).getY();
            int previousX = activeFigures.get(0).getX();
            if (chooseMove == 1 && activeFigures.get(0).getX() != 0) { //контролює вихід вліво за межі масиву
                activeFigures.get(0).setX(activeFigures.get(0).getX() - 1); //зсунули вліво на 1 по борду активну фігуру в листі з поз 0
                board[activeFigures.get(0).getY()][activeFigures.get(0).getX()] = activeFigures.get(0);
                board[previousY][previousX] = null;
            }
            if (chooseMove == 2 && activeFigures.get(0).getX() != board[0].length - 1) {
                activeFigures.get(0).setX(activeFigures.get(0).getX() + 1); //зсунули вліво на 1 по борду активну фігуру в листі з поз 0
                board[activeFigures.get(0).getY()][activeFigures.get(0).getX()] = activeFigures.get(0);
                board[previousY][previousX] = null;
            }
            if (chooseMove == 3 ) {
                activeFigures.get(0).setY(activeFigures.get(0).getY() + 1); //зсунули вниз на 1 по борду активну фігуру в листі з поз 0
                board[activeFigures.get(0).getY()][activeFigures.get(0).getX()] = activeFigures.get(0);
                board[previousY][activeFigures.get(0).getX()] = null;
            }

            deleteIfFix(activeFigures.get(0));
        } catch (ArrayIndexOutOfBoundsException b) {
        } catch (IndexOutOfBoundsException a) {
        }

        round++;


    }

    //Видаляє з активних фігур коли заходить в "фіксовану зону"
    private boolean deleteIfFix(Figure figure) {
        try {

            if (figure.getY() == board.length - 1
                    || board[figure.getY() + 1][figure.getX()] != null) {
                activeFigures.remove(figure);
                return true;
            }

        } catch (ArrayIndexOutOfBoundsException b) {
        } catch (IndexOutOfBoundsException b) {
        }

        return false;
    }

    public void scoreVert(int vert) {
        for (int y = 0; y < board.length; y++) {
            try {
                if (board[y][vert] != null && board[y][vert].getSymbol().equals(board[y - 1][vert].getSymbol()) && board[y][vert].getSymbol().equals(board[y - 2][vert].getSymbol())
                        && board[y][vert].getSymbol().equals(board[y + 1][vert].getSymbol()) && board[y][vert].getSymbol().equals(board[y + 2][vert].getSymbol())) {
                    board[y][vert] = null;
                    board[y - 1][vert] = null;
                    board[y - 2][vert] = null;
                    board[y + 1][vert] = null;
                    board[y + 2][vert] = null;
                    //додає балы коли згорають фігури
                    this.score += 4;
                }


            } catch (ArrayIndexOutOfBoundsException | NullPointerException a) {
            }
            try {
                if (board[y][vert] != null && board[y][vert].getSymbol().equals(board[y - 1][vert].getSymbol()) && board[y][vert].getSymbol().equals(board[y - 2][vert].getSymbol())) {
                    board[y][vert] = null;
                    board[y - 1][vert] = null;
                    board[y - 2][vert] = null;
                    //додає балы коли згорають фігури
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
                if (board[row][y] != null && board[row][y].getSymbol().equals(board[row][y - 1].getSymbol()) && board[row][y].getSymbol().equals(board[row][y - 2].getSymbol())
                        && board[row][y].getSymbol().equals(board[row][y + 1].getSymbol()) && board[row][y].getSymbol().equals(board[row][y + 2].getSymbol())) {
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
                if (board[row][y] != null && board[row][y].getSymbol().equals(board[row][y - 1].getSymbol()) && board[row][y].getSymbol().equals(board[row][y - 2].getSymbol())) {
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
