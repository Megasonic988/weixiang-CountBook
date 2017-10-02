/**
 * CountBookActivity Class
 *
 * Copyright 2017 Kevin Wang
 *
 * @author weixiang
 * @version 1.0
 * @created 2017-09-27
 */

package com.ualberta.kevin.countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.lang.reflect.Type;

/**
 * CountBookActivity controls the main list view interface of the
 * app. It makes use of a CountersAdapter to render the list view.
 * This class loads and saves the counters list from/to a file
 * when the add is started/stopped.
 */

public class CountBookActivity extends AppCompatActivity {

    private static final String FILENAME = "counters.json";

    private ListView counterListView;
    private ArrayList<Counter> counterList = new ArrayList<>();
    private CountersAdapter counterArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterListView = (ListView) findViewById(R.id.counterListView);
        counterArrayAdapter = new CountersAdapter(this, counterList);
        counterListView.setAdapter(counterArrayAdapter);

        loadCountersFromFile();
        if (counterList == null) {
            throw new RuntimeException();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        saveCountersToFile();
    }

    /**
     * Load the counters list from a file in JSON format.
     */
    void loadCountersFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String response = new String();
            for (String line; (line = in.readLine()) != null; response += line);

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            ArrayList<Counter> counters = gson.fromJson(response, listType);

            if (counters != null) {
                counterArrayAdapter.clear();
                counterArrayAdapter.addAll(counters);
                updateCounterListCount();
            }
        } catch (FileNotFoundException e) {
//            counterList = new ArrayList<Counter>();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Save the counters list to a file in JSON format.
     */
    private void saveCountersToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(new File(getFilesDir(), FILENAME), false /*append*/);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            String data = gson.toJson(counterList);
            gson.toJson(counterList, writer);
            writer.flush();
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Start the NewCounterActivity when the add counter
     * button is pressed.
     * @param v
     */
    public void addCounter(View v) {
        Intent intent = new Intent(this, NewCounterActivity.class);
        startActivityForResult(intent, IntentConstants.ADD_COUNTER_INTENT_REQUEST);
    }

    /**
     * Update the value of the list count view.
     */
    private void updateCounterListCount() {
        TextView counterListCount = (TextView) findViewById(R.id.counterListCount);
        counterListCount.setText(counterArrayAdapter.getCount() + " counter" + (counterArrayAdapter.getCount() == 1 ? "" : "s"));
    }

    /**
     * Handle the data passed back from other activities. Operations
     * include add, editing, and deleting counters.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) return; // Return without result action
        if (resultCode == IntentConstants.DELETE_COUNTER_INTENT_RESPONSE) {
            int counterIndex = data.getIntExtra(IntentConstants.INTENT_COUNTER_INDEX, 0);
            Counter counter = counterList.get(counterIndex);
            counterArrayAdapter.remove(counter);
            updateCounterListCount();
            return;
        }

        String counterTitle = data.getStringExtra(IntentConstants.INTENT_COUNTER_TITLE);
        Integer counterInitialValue = data.getIntExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, 0);
        Date counterDate = new Date(data.getLongExtra(IntentConstants.INTENT_COUNTER_DATE, 0));
        String counterComment = data.getStringExtra(IntentConstants.INTENT_COUNTER_COMMENT);

        if (resultCode == IntentConstants.ADD_COUNTER_INTENT_RESULT) {
            Counter counter = new Counter(counterTitle, counterDate, counterInitialValue, counterComment);
            counterArrayAdapter.add(counter);
            TextView counterListCount = (TextView) findViewById(R.id.counterListCount);
            counterListCount.setText(counterArrayAdapter.getCount() + " counter" + (counterArrayAdapter.getCount() > 1 ? "s" : ""));
            updateCounterListCount();
        }

        else if (resultCode == IntentConstants.EDIT_COUNTER_INTENT_RESPONSE) {
            Integer counterCurrentValue = data.getIntExtra(IntentConstants.INTENT_COUNTER_CURRENT_VALUE, 0);
            int counterIndex = data.getIntExtra(IntentConstants.INTENT_COUNTER_INDEX, 0);
            Counter counter = counterArrayAdapter.getItem(counterIndex);
            counter.setName(counterTitle);
            counter.setInitialValue(counterInitialValue);
            counter.setDate(new Date());
            counter.setCurrentValue(counterCurrentValue);
            counter.setComment(counterComment);
            counterArrayAdapter.notifyDataSetChanged();
        }
    }
}
