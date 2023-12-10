package org.example.HW_3.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class FileOutInput {
    
    public static final String FILE_JSON = "students.json";
    public static final String FILE_BIN = "students.bin";
    public static final String FILE_XML = "students.xml";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void serialize(String fileName, ArrayList<Student> students) {        
        try {
            if (fileName.endsWith(".bin")) {                            
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
                for (Student student : students) {            
                out.writeObject(student);
                }
                out.close();                
                System.out.println("Объект student был сериализован и сохранен в файл .bin");

            } else if (fileName.endsWith(".json")) {                
                objectMapper.writeValue(new FileOutputStream(fileName), students);
                System.out.println("Объект student был сериализован и сохранен в файл .json");

            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), students);
                System.out.println("Объект student был сериализован и сохранен в файл .xml");
            }

            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }

    public static ArrayList<Student> deserialize(String fileName) {
        ArrayList<Student> students = new ArrayList<>();

        File file = new File(fileName);
        if(file.exists()) {        
            try {
                if (fileName.endsWith(".bin")) {   
                    FileInputStream fileIn = new FileInputStream(fileName);                                                 
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    while (fileIn.available() > 0) {                    
                        students.add((Student) in.readObject());
                    }                   
                    in.close();

                    System.out.println("\nОбъект student был десериализован из файла .bin");
                    
                } else if (fileName.endsWith(".json")) {
                students = objectMapper.readValue(file, new TypeReference<ArrayList<Student>>() {});
                System.out.println("\nОбъект student был десериализован из файла .json");
                } else if (fileName.endsWith(".xml")) {
                    students = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(ArrayList.class, Student.class));
                    System.out.println("\nОбъект student был десериализован из файла .xml");
                }

            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }       
        }
        return students;            
    }
}