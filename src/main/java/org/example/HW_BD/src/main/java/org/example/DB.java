package org.example;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "1982";

    // region Создание базы данных и наполенение данными:
    // JDBC
    public static void junit() throws RuntimeException {

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement stmt = con.createStatement();
            stmt.execute("DROP SCHEMA  SchoolDB;"); // удаляем схему
            stmt.execute("CREATE SCHEMA SchoolDB;"); // создаем схему
            stmt.execute(
                    "CREATE TABLE  SchoolDB.Courses(id INT NOT NULL AUTO_INCREMENT, title VARCHAR(45) NULL, duration INT NULL, PRIMARY KEY (id));"); // создаем
            // таблицу
            // заполняем таблицу начальными данными
            stmt.execute("INSERT INTO SchoolDB.Courses(id, title, duration) VALUES(id,'Алгебра', 100);");
            stmt.execute("INSERT INTO SchoolDB.Courses(id, title, duration) VALUES(id, 'Геометрия', 50);");
            stmt.execute("INSERT INTO SchoolDB.Courses(id, title, duration) VALUES(id, 'Програмирование', 30);");
            // выводим данные из таблицы в консоль
            ResultSet set = stmt.executeQuery("SELECT * FROM SchoolDB.Courses;");
            while (set.next()) {
                System.out.println(set.getInt("id") + " " + set.getString("title") + " " + set.getInt("duration"));
                // System.out.println(set.getString(3) + " " + set.getString(2) + " " +
                // set.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    // Hibernate
    public static void fillingData() {
        Connector connector = new Connector();
        Session session = connector.getSession();
        Course course = new Course("История", 70);
        session.beginTransaction();
        session.save(course);

        course = new Course("География", 30);
        session.save(course);

        course = new Course("Русский язык", 90);
        session.save(course);

        course = new Course("Английский язык", 50);
        session.save(course);

        course = new Course("Литература", 40);
        session.save(course);

        course = new Course("Физкультура", 30);
        session.save(course);

        session.close();        
    }
    // endregion

    public static void printTable() {
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            List<Course> courses = session.createQuery("FROM Course", Course.class).getResultList();
    
            if (!courses.isEmpty()) {
                // Вывод заголовка таблицы
                System.out.printf("%-5s%-20s%-15s%n", "ID", "Title", "Duration");
                System.out.println("---------------------------------------------");
    
                // Вывод данных таблицы
                for (Course course : courses) {
                    System.out.printf("%-5d%-20s%-15d%n", course.getId(), course.getTitle(), course.getDuration());
                }
            } else {
                System.out.println("\nТаблица пуста!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AddData(String title, int duration) {
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            Course course = new Course(title, duration);
            session.beginTransaction();
            session.save(course);
            session.close();
            System.out.println("\nДобавлена запись: \n");
            getByTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    public static void setTestData() {
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            String hql = "FROM Course where id = :id";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("id", 1);
            Course course = query.getSingleResult();
            System.out.println(course);
            course.setTitle("Черчение");
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();
            System.out.println("\nЗамена записи под id = 1 на Черчение.\n");
            printTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    public static void getById(int id) {
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            Course course = session.get(Course.class, id);
            if (course != null) {
                System.out.println(course);
            } else {
                System.out.println("Курс с id=" + id + " не найден.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getByTitle(String title) {
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            String hql = "FROM Course WHERE title = :title";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("title", title);
            List<Course> courses = query.getResultList();

            if (!courses.isEmpty()) {
                courses.forEach(System.out::println);
            } else {
                System.out.println("Курсы с заголовком '" + title + "' не найдены.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void filterByDuration(int minDuration, int maxDuration) {
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            String hql = "FROM Course WHERE duration BETWEEN :minDuration AND :maxDuration";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("minDuration", minDuration);
            query.setParameter("maxDuration", maxDuration);
            List<Course> courses = query.getResultList();

            if (!courses.isEmpty()) {
                courses.forEach(System.out::println);
            } else {
                System.out.println(
                        "Курсы с продолжительностью от " + minDuration + " до " + maxDuration + " не найдены.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Удаляет данные из базы данных в зависимости от переданного идентификатора.
     * Идентификатор может быть как числовым (Integer)- id, так и строковым (String) - наименование курса.
     *
     * @param identifier Идентификатор для удаления данных (может быть Integer или String).
     */
    public static void deleteData(Object identifier) {
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            Transaction transaction = session.beginTransaction();

            if (identifier instanceof Integer) {
                // Если передан Integer, значит, удаляем по id
                int id = (int) identifier;
                Course course = session.get(Course.class, id);
                if (course != null) {
                    session.delete(course);
                    System.out.println("Запись с id=" + id + " успешно удалена.");
                } else {
                    System.out.println("Курс с id=" + id + " не найден.");
                }
            } else if (identifier instanceof String) {
                // Если передан String, значит, удаляем по title
                String title = (String) identifier;
                String hql = "FROM Course WHERE title = :title";
                Query<Course> query = session.createQuery(hql, Course.class);
                query.setParameter("title", title);
                List<Course> courses = query.getResultList();

                if (!courses.isEmpty()) {
                    courses.forEach(session::delete);
                    System.out.println("Записи с заголовком '" + title + "' успешно удалены.");
                } else {
                    System.out.println("Курсы с заголовком '" + title + "' не найдены.");
                }
            } else {
                System.out.println("Неверный тип данных для удаления.");
            }

            transaction.commit();
            printTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
     public static void deleteAllData() {
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            Transaction transaction = session.beginTransaction();
            List<Course> magics = session.createQuery("FROM Course ", Course.class).getResultList();
            magics.forEach(course -> session.delete(course));
            transaction.commit();
            System.out.println("\nУдаление всех записей.\n");
            printTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //  public static void getAllData() {
    //     Connector connector = new Connector();
    //     try (Session session = connector.getSession()) {
    //         List<Course> books = session.createQuery("FROM Course", Course.class).getResultList();
    //         books.forEach(System.out::println);
    //         printTable();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    
}
