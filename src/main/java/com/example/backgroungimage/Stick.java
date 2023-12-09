package com.example.backgroungimage;

public class Stick extends GameObject{
    private double height;

    Stick(double x, double y, double height){
        super(x,y);
        this.height=height;
    }
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public int increaseHt()
    {
        return 1;
    }
    public void move()
    {

    }
}