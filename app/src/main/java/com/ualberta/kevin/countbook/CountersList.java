package com.ualberta.kevin.countbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by weixiang on 10/1/17.
 */

public class CountersList extends ArrayList<Counter> {

    public CountersList(FileInputStream fis) {
        super();
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
        ArrayList<Counter> counters = gson.fromJson(in, listType);
        this.addAll(counters);
    }
}
