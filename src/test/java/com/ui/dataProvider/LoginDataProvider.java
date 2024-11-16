package com.ui.dataProvider;

import com.google.gson.Gson;
import com.pojo.TestData;
import com.pojo.User;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "LoginDataProvider")
    public Iterator<Object[]> loginDataProvider(){

        Gson gson = new Gson();

        File file = new File("testData//loginData.json");

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        assert fileReader != null;

        TestData data = gson.fromJson(fileReader, TestData.class);

        List<Object[]> loginData = new ArrayList<Object[]>();

        for (User user: data.getData()){

            loginData.add(new Object[]{user});
        }

        return loginData.iterator();
    }

}
