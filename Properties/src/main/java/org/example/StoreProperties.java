package org.example;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class StoreProperties {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("spring.aplication.name", "Santiago");
        properties.put("spring.aplication.last.name", "Perez");
        properties.put("spring.aplication.age", "25");

        try {
            // Exportar propiedades a XML
            //OutputStream outputStreamXML = new FileOutputStream("src/main/resources/application.xml");
            //properties.storeToXML(outputStreamXML, "Algun comentario para mis properties");

            // Exportar propiedades a PROPERTIES
            OutputStream outputStream = new FileOutputStream("src/main/resources/application.properties");
            properties.store(outputStream, "Algun comentario para mis properties");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}