package org.example.HW_2.Task1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        // Создание массива объектов типа Animal
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Dog("Buddy", 3, "Golden Retriever"));
        animals.add(new Dog("Buddy", 3, "Golden Retriever"));
        animals.add(new Cat("Whiskers", 2, true));
        animals.add(new Dog("Max", 4, "German Shepherd"));
        animals.add(new Cat("Mittens", 1, false));
        animals.add(new Dog("Grey", 5, "Pit-bull"));

        InfoAnimal.getInfo(animals);


    }
}
