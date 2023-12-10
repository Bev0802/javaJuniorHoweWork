package org.example.HW_3.Task;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Student implements Serializable {   
   
    private String name;
    private int age;
    /* @param gpa - средний балл*/ 
    @JsonIgnore
    private transient double gpa; // средний балл
    
    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }    

    public Student(){};   
     
    
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public double getGpa() {
        return gpa;
    }
    
    @Override
    public String toString() {
        return "Студент: " + name + ", " + age + ", " + gpa;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    
}