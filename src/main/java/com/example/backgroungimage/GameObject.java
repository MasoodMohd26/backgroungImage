package com.example.backgroungimage;
public class GameObject {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public GameObject() {

    }

    public boolean isMovable()
    {
        return false;

    }


}