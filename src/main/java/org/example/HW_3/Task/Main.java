package org.example.HW_3.Task;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {        
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Ivan", 18, 4.5));
        students.add(new Student("Petr", 19, 5.0));
        students.add(new Student("Alexey", 20, 3.5));
        students.add(new Student("Sergey", 21, 4.0));
        students.add(new Student("Viktor", 22, 3.0));
        students.add(new Student("Semen", 23, 4.5));
        
        System.out.printf("Список студентов до сериализации: %s\n\n" , (Arrays.toString(students.toArray())));
        
        FileOutInput.serialize(FileOutInput.FILE_BIN, students);
        FileOutInput.serialize(FileOutInput.FILE_JSON, students);
        FileOutInput.serialize(FileOutInput.FILE_XML, students);
        
        System.out.printf("Список студентов .bin: %s\n" , (Arrays.toString((FileOutInput.deserialize(FileOutInput.FILE_BIN)).toArray())));
        System.out.printf("Список студентов .json: %s\n" , (Arrays.toString((FileOutInput.deserialize(FileOutInput.FILE_JSON)).toArray())));
        System.out.printf("Список студентов .xml: %s\n" , (Arrays.toString((FileOutInput.deserialize(FileOutInput.FILE_XML)).toArray())));
    }       
    
}