package com.example.backgroungimage;


public class Hero extends GameObject{
    private boolean isAlive;
    private int height;
    private double speed;

    public Hero(int x, int y, boolean isAlive, int height, double speed) {
        super(x, y);
        this.isAlive = isAlive;
        this.height = height;
        this.speed = speed;
    }

    public Hero(boolean isAlive, int height, double speed) {
        this.isAlive = isAlive;
        this.height = height;
        this.speed = speed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public void move()
    {

    }
}
