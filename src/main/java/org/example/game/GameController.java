package org.example.game;

public class GameController {
    private static Board board;

    //Вибір складності
    public GameController() {
        Level level = InputController.readLevel("\nChoose level:\n1 - Easy\n2 - Medium\n3 - Hard");
        board = new Board(level);
        startGame();

    }
    private void startGame(){
        board.print();
        InputController.readInt("Move or Finish");

        int direction = InputController.readInt("\nChoose your move:\n1. Left\n2. Right\n3. Bottom" + "\n4.Quit");
        if(direction==4){


        }
        board.move(direction);
        boolean isFinish = board.isFinish();
        if (isFinish){
            finish();
        }
        startGame();
    }
    private static boolean finish(){
        System.out.println("Game is over");
        String playerName = InputController.readString("Please enter your name(no more 10 letters)");
        if (playerName.length()>10) {
            String cutName = playerName.substring(0, 10);
            playerName = cutName;
        }
        User user = new User(playerName,board.getScore(),board.getRound(),board.getLevel()); //Створення об'єкта юзера
        boolean result = StorageController.save(user); //збереження даних юзера в хранилище
        if (result){
            System.out.println("Record saved");
        }
        else {
            System.out.println("Error to save record");
        }

        return false;
    }
}

