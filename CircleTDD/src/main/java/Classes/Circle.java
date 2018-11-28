/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.math.BigDecimal;

/**
 *
 * @author mauriciobedoya
 */
public class Circle {
    private int radius;
    private COLORS color;
    private void checkRadiusValue(int value){
        if(value < 0){
            throw new IllegalArgumentException("Negative radius not allowed during construction!!");
        }
    }

    Circle(int radius, COLORS color) {
        checkRadiusValue(radius);
        this.radius = radius;
        this.color = color;
    }

    Circle(int radius) {
        checkRadiusValue(radius);
        this.radius = radius;
        this.color = COLORS.BLACK;
    }

    public int getRadius() {
        return this.radius;
    }
    
    public void setRadius(int newRadius){
        if(newRadius < 0){
            throw new IllegalArgumentException("Negative radius setting not allowed !!");
        }
        this.radius = newRadius;
    }

    void setColor(COLORS newColor) {
        this.color = newColor;
    }

    public COLORS getColor() {
        return this.color;
    }

    public double getArea() {
        return Math.PI * (this.radius * this.radius);
    }

    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }
}
