package com.company;

public class Circle extends Shape {
    public double getArea(double d) {
        return Math.PI * d *d;
    }

    public double getPerimeter(double d) {
        return Math.PI * d * 2;
    }
}