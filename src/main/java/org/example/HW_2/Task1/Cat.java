package org.example.HW_2.Task1;

/// Класс-наследник Cat
class Cat extends Animal {
    private boolean isIndoor;

    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }

    // Переопределенный метод makeSound для кошки
    @Override
    public void makeSound() {
        System.out.println(name + " says Meow!");
    }

    // Уникальный метод для класса Cat
    public void scratchFurniture() {
        System.out.println(name + " is scratching the furniture.");
    }
}

