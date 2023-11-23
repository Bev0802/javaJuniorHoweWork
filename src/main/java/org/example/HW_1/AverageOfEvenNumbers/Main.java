package org.example.HW_1.AverageOfEvenNumbers;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаем экземпляр класса Actions
        Actions actions = new Actions();

        //получаем рандомный список из класса RandomArrayListExample.
        List<Integer> randomList = actions.generateRandomList(100, 10);        
        System.out.println("Рандомный список: " + randomList);

        //выводим среднее значение четных чисел.      
        System.out.println("\nСреднее значение всех четных чисел: " + actions.getAverageOfEvenNumbers(randomList));        
    } 

}