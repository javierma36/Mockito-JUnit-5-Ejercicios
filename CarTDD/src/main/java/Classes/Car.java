/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Set;
import javax.naming.SizeLimitExceededException;

/**
 *
 * @author mauriciobedoya
 */
public class Car {

    private String color;
    private Engine engine;
    private Set<Passenger> passengers;
    private int passengerCapacity;

    public Car(String color, int passengerCapacity, Engine engine, Set passengers) {
        this.color = color;
        this.passengerCapacity = passengerCapacity;
        this.engine = engine;
        this.passengers = passengers;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public String getColor() {
        return this.color;
    }

    public void addPassenger(Passenger passenger) throws SizeLimitExceededException {
        if (passengerCapacity == 0) {
            throw new SizeLimitExceededException();
        }
        passengers.add(passenger);
        --passengerCapacity;
    }

    public int remainingCapacity() {
        return passengerCapacity;
    }

    public void removePassenger(Passenger passenger) {
        if (passengers.remove(passenger)) {
            ++passengerCapacity;
        }else{
            throw new IllegalArgumentException("Passenger not found inside the Car !!");
        }

    }

}
