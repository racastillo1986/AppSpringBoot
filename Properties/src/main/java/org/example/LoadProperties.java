package org.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            // XML
            //InputStream inputStreamXML = new FileInputStream("src/main/resources/application.xml");
            //properties.loadFromXML(inputStreamXML);

            // PROPERTIES
            InputStream inputStream = new FileInputStream("src/main/resources/application.properties");
            properties.load(inputStream);

            System.out.println(properties);
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
