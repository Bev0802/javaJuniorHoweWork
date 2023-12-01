package org.example.HW_2.Task1;

// Класс-наследник Dog
class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    // Переопределенный метод makeSound для собаки
    @Override
    public void makeSound() {
        System.out.println(name + " says Woof!");
    }

    // Уникальный метод для класса Dog
    public void fetch() {
        System.out.println(name + " is fetching the ball.");
    }
}
