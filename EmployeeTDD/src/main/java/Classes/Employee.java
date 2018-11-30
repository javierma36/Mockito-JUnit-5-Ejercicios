/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author mauriciobedoya
 */
class Employee {

    private Country country;
    private String fname, lname;
    private int id;
    private double monthlySalary;
    
    private Employee(){}

    public Country getCountry() {
        return this.country;
    }

    public String getFname() {
        return this.fname;
    }

    public String getLname() {
        return this.lname;
    }

    public int getId() {
        return this.id;
    }

    public double getSalary() {
        return this.monthlySalary;
    }
    
    public void setCountry(Country newCountry){
        this.country = newCountry;
    }

    void setFname(String fname) {
        this.fname = fname;
    }

    void setLname(String lname) {
        this.lname = lname;
    }

    void setId(int id) {
        this.id = id;
    }

    void setSalary(double monthlySalary) {
        if(monthlySalary <= 0.0){
            throw new IllegalArgumentException("Salary is" + monthlySalary + ". Negative salary setting not allowed !!");
        }
        this.monthlySalary = monthlySalary;
    }

    double getAnnualSalary() {
        return this.monthlySalary * 12;
    }

    double salaryIncrease(double salaryIncreasePercentage) {
        if(salaryIncreasePercentage < 0){
            throw new IllegalArgumentException("Error: negative salary increase not possible !!");
        }
        return this.monthlySalary * (1 + salaryIncreasePercentage);
    }

    public static class Builder {
        private Country country;
        private String fname, lname;
        private int id;
        private double monthlySalary;
        
        public Builder withCountry(Country country){
            this.country = country;
            return this;
        }
        public Builder fname(String fname){
            this.fname = fname;
            return this;
        }
        public Builder lname(String lname){
            this.lname = lname;
            return this;
        }
        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder monthlySalary(double salary){
            if(salary < 0){
                throw new IllegalArgumentException("Salary is " + salary + ". Negative salary during building not allowed !!");
            }
            this.monthlySalary = salary;
            return this;
        }
        public Employee build(){
            Employee employee = new Employee();
            employee.country = this.country;
            employee.fname = this.fname;
            employee.lname = this.lname;
            employee.id = this.id;
            employee.monthlySalary = this.monthlySalary;
            return employee;
        }
    }

}
