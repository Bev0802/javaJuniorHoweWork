## Урок 4. Базы данных и инструменты взаимодействия с ними
1. Создайте базу данных (например, SchoolDB).
2. В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
3. Настройте Hibernate для работы с вашей базой данных.
4. Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
5. Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
6. Убедитесь, что каждая операция выполняется в отдельной транзакции.

## _Проблемма_

    При запуске проэта в 'IntelliJ IDEA ULT 2022.1.2'  переодически выдает ошибки сборки, поэтому приходится загружать из HW_BD(отдельный проэкт), а в 'VS Code запускается' без проблем при запуске из HW_4(часть проэта JavaJunioHowework).

## _Описание_

Мой проект представляет собой консольное приложение на языке Java для управления базой данных, используя как JDBC, так и Hibernate. В классе Main реализована основная логика приложения, включая инициализацию, вывод информации о курсах и продолжительности, а также вызов различных методов для работы с базой данных.

Класс DB содержит методы для взаимодействия с базой данных, включая создание и наполнение таблицы, вывод данных в консоль, изменение данных, поиск по id и title, фильтрацию по продолжительности, добавление новых записей и удаление данных по id или title. Также реализован метод для вывода данных таблицы в удобочитаемом формате таблицы в консоль.

Сущность Course представляет объект "Курс", отображаемый в базе данных, и содержит соответствующие поля id, title и duration.

Класс Connector предоставляет удобный интерфейс для установления соединения с базой данных, используя Hibernate.