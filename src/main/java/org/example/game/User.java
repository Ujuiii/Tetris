package org.example.game;

public class User implements Comparable<User>{
    private String name;
    private int score;
    private int round;
    private Level level;

    public User(String name, int score, int round, Level level){
        this.name = name;
        this.score = score;
        this.round = round;
        this.level = level;
    }
    public int compareTo(User user){
        return  user.score- this.score;
    }
    public String toString(){
        return "User:"+name+" Level:"+level+" Round:"+ round+" Score:"+score;
    }
    public int getScore(){
        return score;
    }
    public String getName(){
        return name;
    }
    public int getRound(){
        return round;
    }
    public Level getLevel(){
        return level;
    }
}

