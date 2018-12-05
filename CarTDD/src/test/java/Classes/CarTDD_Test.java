/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.HashSet;
import javax.naming.SizeLimitExceededException;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 *
 * @author mauriciobedoya
 */
public class CarTDD_Test {
    @Mock  // Dummy (No method call in this object)
    private Engine engine;
    @Mock // Dummy (No method call in these objects)
    private Passenger p1,p2,p3,p4,p5;
    @Spy //Functionality 
    private HashSet<Passenger> passengers;
    private Car car;
    
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        car = new Car("Black", 4, engine, passengers);
    }
    
    @Test
    public void carWithEngine(){
        assertThat(car.getEngine()).isNotNull();
    }
    
    @Test
    public void carWithPassengers(){
        passengers.add(p1);
        assertThat(car.getPassengers().size()).isGreaterThan(0);
    }
    
    @Test
    public void carHasColor(){
        assertThat(car.getColor()).isEqualTo("Black");
    }
    
    @Test
    public void addPassenger() throws SizeLimitExceededException{
        car.addPassenger(p1);
        assertThat(passengers.size()).isEqualTo(1);
    }
    
    @Test
    public void remainingCapacityInCarAfterAddingOnePassenger() throws SizeLimitExceededException{
        car.addPassenger(p1);
        assertThat(car.remainingCapacity()).isEqualTo(3);
    }
    
    @Test
    public void addMorePassengersThanCarCapacityThrowsException()throws SizeLimitExceededException{
        car.addPassenger(p1);
        car.addPassenger(p2);
        car.addPassenger(p3);
        car.addPassenger(p4);    
        Assertions.assertThrows(SizeLimitExceededException.class, ()->car.addPassenger(p5));
    }
    
    @Test
    public void addAndRemovingSamePassenger() throws SizeLimitExceededException{
        car.addPassenger(p1);
        car.removePassenger(p1);
        assertThat(car.remainingCapacity()).isEqualTo(4);
    }
    
    @Test
    public void removingNonExistingPassenger() throws SizeLimitExceededException{
        car.addPassenger(p1);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,()->car.removePassenger(p2));
        assertThat(exception.getMessage()).isEqualTo("Passenger not found inside the Car !!");
    }
}
