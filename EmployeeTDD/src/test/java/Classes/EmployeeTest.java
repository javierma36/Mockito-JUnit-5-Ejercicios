/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import static org.fest.assertions.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author mauriciobedoya
 */
public class EmployeeTest {
    Employee employee;
    @BeforeEach
    public void setUp(){
        employee = new Employee.Builder()
                .withCountry(Country.COLOMBIA)
                .fname("Mauricio")
                .lname("Bedoya")
                .id(1)
                .monthlySalary(100.0)
                .build();
    }
    @Test
    public void employeeBuilderGetters(){
        Assertions.assertAll("Builder Construction",
                ()->{assertThat(employee.getCountry()).isEqualTo(Country.COLOMBIA);},
                ()->{assertThat(employee.getFname()).isEqualTo("Mauricio");},
                ()->{assertThat(employee.getLname()).isEqualTo("Bedoya");},
                ()->{assertThat(employee.getId()).isEqualTo(1);},
                ()->{assertThat(employee.getSalary()).isEqualTo(100.0);} 
        );
    }
    
    @ParameterizedTest
    @EnumSource(value=Country.class,names={"COLOMBIA", "EEUU", "SPAIN"})
    public void employeeCountryChange(Country country){
        employee.setCountry(country);
        assertThat(employee.getCountry()).isEqualTo(country);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"Alex","Samuel","Luz"})
    public void employeeFnameChange(String fname){
        employee.setFname(fname);
        assertThat(employee.getFname()).isEqualTo(fname);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"Buda","Klein","Velez"})
    public void employeeLnameChange(String lname){
        employee.setLname(lname);
        assertThat(employee.getLname()).isEqualTo(lname);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2,3,4,5})
    public void employeeIdChange(int id){
        employee.setId(id);
        assertThat(employee.getId()).isEqualTo(id);
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {200.3, 109, 98, 1000})
    public void employeeSalaryChange(double salary){
        employee.setSalary(salary);
        assertThat(employee.getSalary()).isEqualTo(salary);
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.9, -100.9, -0.0})
    public void employeeNegativeSalarySettingThrowsException(double monthlySalary){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,()->{employee.setSalary(monthlySalary);});
        assertThat(exception.getMessage()).isEqualTo("Salary is" + monthlySalary + ". Negative salary setting not allowed !!");
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -20.0, -100.0, -200.0})
    public void employeeNegativeSalaryContructionThrowsException(double monthlySalary){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            Employee employee = new Employee.Builder().monthlySalary(monthlySalary).build();
        });
        assertThat(exception.getMessage()).isEqualTo("Salary is " + monthlySalary + ". Negative salary during building not allowed !!");
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {100,200,300,400})
    public void employeeAnualSalary(double monthlySalary){
        double annualSalary = monthlySalary * 12;
        employee.setSalary(monthlySalary);
        assertThat(annualSalary).isEqualTo(employee.getAnnualSalary());
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {1000.0,2000.0})
    public void employeeAnualSalaryDifferenceAfterMonthlySalaryIncreaseMustBePositive(double monthlySalary){
        double oldAnnualSalary = employee.getAnnualSalary() * 12;
        employee.setSalary(monthlySalary);
        double newAnnualSalary = employee.getAnnualSalary() * 12;  // Initial Salary is 100.0
        assertThat((newAnnualSalary - oldAnnualSalary)).isGreaterThan(0.0);
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {0.05, 0.1})
    public void employeeSalaryPositiveIncrease(double salaryIncreasePercentage){
        double newSalary = employee.getSalary() * (1+salaryIncreasePercentage);
        assertThat(newSalary).isEqualTo(employee.salaryIncrease(salaryIncreasePercentage));
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {-0.02, -0.05})
    public void employeeSalaryNegativeIncreaseThrowsException(double salaryIncreasePercentage){
        IllegalArgumentException exception = Assertions
                .assertThrows(IllegalArgumentException.class, ()->{employee.salaryIncrease(salaryIncreasePercentage);});
        assertThat(exception.getMessage()).isEqualTo("Error: negative salary increase not possible !!");
    }
}
