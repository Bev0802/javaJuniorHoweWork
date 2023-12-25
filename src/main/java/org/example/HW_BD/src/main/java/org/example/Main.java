package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Курсы / продолжительность: ");
        DB.junit(); // удаляет и создает базу данных SchoolDB в ней таблицу Сourses, заполняет ее с помощью JDBC.
        DB.fillingData(); // наполняет таблицу данными через Hibernate

        DB.printTable(); // выводит данные из таблицы

        DB.setTestData(); // изменяет данные
        
        DB.getById(5); // поиск по id
        DB.getByTitle("Английский язык"); // поиск по предмету
        DB.filterByDuration(10, 50); // поиск по продолжительности

        DB.AddData("Немецкий язык", 50); // добавляет данные

        DB.deleteData(5); 
        DB.deleteData("Английский язык");

       //DB.deleteAllData(); // удаляет все данные из таблицы
    }
}
