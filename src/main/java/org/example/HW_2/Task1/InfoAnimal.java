package org.example.HW_2.Task1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

class InfoAnimal {
    static <T extends Animal> void getInfo(List<T> animals) throws IllegalAccessException {
        for (T animal : animals) {
            // Вывод информации о классе-наследнике
            System.out.println("Animal type: " + animal.getClass().getSimpleName());

            // Вывод информации о полях name и age из базового класса Animal
            try {
                System.out.println("name: " + Animal.class.getDeclaredField("name").get(animal));
                System.out.println("age: " + Animal.class.getDeclaredField("age").get(animal));
                // Вывод информации об уникальных полях.
                for (Field field : animal.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    System.out.println(field.getName() + ": " + field.get(animal));
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            // Вызов метода makeSound, если такой метод присутствует
            try {
                animal.getClass().getMethod("makeSound").invoke(animal);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println(animal.getClass().getSimpleName() + " doesn't make a sound.");
            }

            // Вызов уникального метода, если объект является экземпляром Dog или Cat
            if (animal instanceof Dog) {
                ((Dog) animal).fetch();
            } else if (animal instanceof Cat) {
                ((Cat) animal).scratchFurniture();
            }

            System.out.println("--------------------");
        }
    }
}
