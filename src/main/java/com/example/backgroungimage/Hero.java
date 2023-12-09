package com.example.backgroungimage;



//Singleton class is being used here as we need only once instance of Hero to be there in the game.
public class Hero extends GameObject{
    private static Hero stickHero = null;
    private boolean isAlive;
    private double height;

    public static Hero getInstance(double x,double y,boolean isAlive,double height){
        if (stickHero==null){
            stickHero = new Hero(x,y,isAlive,height);
        }
        return stickHero;
    }
    Hero(double x, double y, boolean isAlive, double height) {
        super(x, y);
        this.isAlive = isAlive;
        this.height = height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void move()
    {

    }

}