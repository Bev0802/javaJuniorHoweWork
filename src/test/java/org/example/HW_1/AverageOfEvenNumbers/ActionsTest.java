package org.example.HW_1.AverageOfEvenNumbers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ActionsTest {
    private Actions actions;

    @BeforeEach
    void BeforeEach(){ actions = new Actions(); }

    @AfterEach
    void AfterEach(){actions = null;}

    @Test
    void generateRandomList() {
        Actions actions = new Actions();

        int toNum = 100;
        int listSize = 10;

        List<Integer> randomList = actions.generateRandomList(toNum, listSize);

        // Проверяем, что список не пустой
        assertNotNull(randomList);

        // Проверяем, что размер списка соответствует ожидаемому
        assertEquals(listSize, randomList.size());

        // Проверяем, что все числа в списке находятся в диапазоне от 1 до toNum
        for (int number : randomList) {
            assertTrue(number >= 1 && number <= toNum);
        }
    }

    @Test
    void getAverageOfEvenNumbers() {
        // Тест среднего значения для списка четных чисел
        List<Integer> evenNumbersList = List.of(2, 4, 6, 8, 10);
        double averageEven = actions.getAverageOfEvenNumbers(evenNumbersList);
        assertEquals(6.0, averageEven);

        // Тест среднего значения для списка без четных чисел
        List<Integer> oddNumbersList = List.of(1, 3, 5, 7, 9);
        double averageOdd = actions.getAverageOfEvenNumbers(oddNumbersList);
        assertEquals(0.0, averageOdd);
    }
}