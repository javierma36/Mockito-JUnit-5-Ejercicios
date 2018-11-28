/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;


import org.fest.assertions.Assertions;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mauriciobedoya
 * GRACIAS Y ÉXITOS.
 */
public class CircleTest {
    private Circle circle;
    private Circle circle1;
    @BeforeEach
    public void setUp(){
        circle = new Circle(10,COLORS.RED);
        circle1 = new Circle(10);
    }
    
    @Test
    public void circleConstructor1(){
        assertThat(circle1.getColor()).isEqualTo(COLORS.BLACK);
    }
    
    @Test
    public void circleRadiusSetting(){
        Assertions.assertThat(circle.getRadius()).isEqualTo(10);
    }
    
    @Test
    public void circlePositiveRadiusSetting(){
        Assertions.assertThat(circle.getRadius()).isGreaterThan(0);
    }
    
    @Test
    public void negativeCircleRadiusSettingThrowsIllegalArgumentException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->circle.setRadius(-10));
        assertThat(exception.getMessage()).isEqualTo("Negative radius setting not allowed !!");
    }
    
    @Test
    public void negativeCircleRadiusConstructor1ThrowsIllegalArgumentException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->new Circle(-10));
        assertThat(exception.getMessage()).isEqualTo("Negative radius not allowed during construction!!");
    }
    
    @Test
    public void negativeCircleRadiusConstructorThrowsIllegalArgumentException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->new Circle(-10,COLORS.RED));
        assertThat(exception.getMessage()).isEqualTo("Negative radius not allowed during construction!!");
    }
    
    @Test
    public void radiusSettingChange(){
        circle.setRadius(12);
        assertThat(circle.getRadius()).isEqualTo(12);
    }
    
    @Test
    public void colorSettingChange(){
        circle.setColor(COLORS.WHITE);
        assertThat(circle.getColor()).isEqualTo(COLORS.WHITE);
    }
    
    @Test
    public void circleArea(){
        double area = Math.PI * Math.pow(circle.getRadius(), 2);
        assertThat(circle.getArea()).isEqualTo(area);
    }
    
    @Test
    public void circlePerimeter(){
        double perimeter = 2 * Math.PI * circle.getRadius();
        assertThat(circle.getPerimeter()).isEqualTo(perimeter);
    }
    
    // Los dos test finales pueden mejorarse buscan un limite o valor al cual convergen como minimo.
    @Test
    public void circlePerimeterIncreaseIfRadiusIncrease(){
        double initPerimeter = circle.getPerimeter();
        circle.setRadius(20);
        double endPerimeter = circle.getPerimeter();
        assertThat((endPerimeter - initPerimeter)).isGreaterThan(0);
    }
    
    @Test
    public void circleAreaIncreaseIfRadiusIncrease(){
        double initArea = circle.getArea();
        circle.setRadius(20);
        double endArea = circle.getArea();
        assertThat((endArea - initArea)).isGreaterThan(0);
    }
}
