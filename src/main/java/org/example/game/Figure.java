package org.example.game;

public class Figure {
    private String symbol;
    private int x;
    private int y;

    public Figure(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol(){
        return this.symbol;
    }
    public String toString(){
        return " "+symbol+" ";
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
}
