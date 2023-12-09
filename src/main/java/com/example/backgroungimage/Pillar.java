package com.example.backgroungimage;

public class Pillar extends GameObject {
    private double height;
    private double width;

    public Pillar(double x,double y,double height,double width){
        super(x,y);
        this.height=height;
        this.width=width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}