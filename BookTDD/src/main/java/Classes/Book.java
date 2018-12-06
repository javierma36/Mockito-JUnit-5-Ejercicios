/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.Autor;

/**
 *
 * @author mauriciobedoya
 */
public class Book {
    private String name;
    private Autor autor;
    private double price;

    public Book(String name, Autor autor, double price) {
        this.name = name;
        this.autor = autor;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Autor getAutor() {
        return this.autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString(){
        return this.getName() + " by " + this.getAutor().getName() 
                + "(" + this.getAutor().getGender() + ") at " + this.getAutor().getEmail();
    }
    
}
