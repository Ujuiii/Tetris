package org.example.game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StorageController {

    public static boolean save(User user){
        //створення об'єкту для запису у файл з можливістью дописування в існуючий файл
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Score.txt"),true))){
            //дописування в файл рядка з переносом на наступний
            writer.append(user.getName()+"           "
                    + user.getRound() +"               "
                    +user.getLevel()+"         "
                    +user.getScore()
                    + "\n");
            return true;
        }
        catch (IOException e){

        }
        return false;
    }

    public static List<User> find(Level level){
        List<User> users = new ArrayList<>();
        //Створення об'єкту для зчитування з текстового файлу
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("Score.txt")))) {
            String line = reader.readLine();
            while ((line = reader.readLine())!=null){
                //Розбиття стрінгів на окремі стрінги пробілами
                String[] data = line.split("[ ]+");
                Level currentLevel = Level.valueOf(data[2]);
                //Створення нового юзера
                if (currentLevel==level){
                    User user = new User(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[3]),currentLevel);
                    users.add(user);
                }
            }
            Collections.sort(users);

        } catch (IOException e){

        }
        return users;
    }
    public static User findMaxScore(){
        User user = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("Score.txt")))) {
            String line = reader.readLine();

            while ((line = reader.readLine())!=null){
                String[] data = line.split("[ ]+");
                Level currentLevel = Level.valueOf(data[2]);
                if (user==null){
                    user = new User(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[3]),currentLevel);
                }
                //порівняння з існуючим юзером та юзерами в файлу
                else if(user.getScore()<Integer.parseInt(data[3])) {
                    user = new User(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[3]),currentLevel);
                }


            }


        } catch (IOException e){

        }return user;
    }


}
