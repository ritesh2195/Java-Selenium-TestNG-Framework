package com.utility;

import com.google.gson.Gson;
import com.pojo.Config;
import com.pojo.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONUtility {

    public static Environment readJson(){

        Gson gson = new Gson();

        File file = new File("src//test//resources//config/config.json");

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert fileReader != null;

        Config config = gson.fromJson(fileReader, Config.class);

        return config.getEnvironment().get("QA");

    }
}
