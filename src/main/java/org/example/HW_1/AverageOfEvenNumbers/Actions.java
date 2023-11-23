package org.example.HW_1.AverageOfEvenNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Actions {
    int toNum;
    int listSize;   
    
    /**
     * Метод для генерации списка случайных чисел
     * @return
     */
    public List<Integer> generateRandomList(int toNum, int listSize) {
        List<Integer> randomNumbers = new ArrayList<>();

        // Генерируем рандомные числа от 1 до toNum и добавляем их в ArrayList
        Random random = new Random();
        for (int i = 0; i < listSize; i++) {
            randomNumbers.add(random.nextInt(toNum) + 1);
        }
        return randomNumbers;
    }

    /**
     * Метод getAverageOfEvenNumbers вычисляет среднее значение четных чисел из списка целых чисел.
     * 
     * @param
     * @return Double - Среднее значение округленное до 2 знаков после запятой.
     */
    public double getAverageOfEvenNumbers(List<Integer> numbers) {
        double average = numbers.stream()
                .filter(num -> num % 2 == 0) // Фильтруем четные числа
                .peek(num -> System.out.print(num + " ")) // Выводим каждую строку с пробелом
                .mapToDouble(Integer::doubleValue) // Преобразуем Integer в double
                .average() // Вычисляем среднее значение
                .orElse(0.0); // Если списка нет, то возвращаем 0.0

        return Math.round(average * 100.0) / 100.0;
    }

}

