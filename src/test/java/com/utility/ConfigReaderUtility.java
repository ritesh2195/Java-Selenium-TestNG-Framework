package com.utility;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReaderUtility {

    private static final ConfigReaderUtility configReaderUtility = new ConfigReaderUtility();

    private static final String CONFIG_FILE_PATH = "src//test//resources//config/QA.properties";

    private final Properties properties;

    private ConfigReaderUtility(){
        properties = new Properties();

        try {

            File file = new File(CONFIG_FILE_PATH);

            FileInputStream fileInputStream = new FileInputStream(file);

            properties.load(fileInputStream);
        }

        catch (Exception ignored){

        }

    }

    public static ConfigReaderUtility getInstance(){

        return configReaderUtility;
    }

    public String getURL(){
        return properties.getProperty("URL");
    }

    public String getPropertyValue(String property){
        return properties.getProperty(property);
    }
}
