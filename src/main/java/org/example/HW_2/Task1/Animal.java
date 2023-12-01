package org.example.HW_2.Task1;

import java.lang.reflect.Method;

// Абстрактный класс Animal
abstract class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Абстрактный метод makeSound
    public abstract void makeSound();
}
