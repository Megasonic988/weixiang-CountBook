package com.ualberta.kevin.countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class CountBookActivity extends AppCompatActivity {

    private ListView counterListView;
    private ArrayList<Counter> counterList;
    private ArrayAdapter<Counter> counterArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterListView = (ListView) findViewById(R.id.counterListView);
        counterList = new ArrayList<>();
        counterArrayAdapter = new ArrayAdapter<Counter>(this, android.R.layout.simple_list_item_1, counterList);
        counterListView.setAdapter(counterArrayAdapter);
    }

    public void addCounter(View v) {
        Intent intent = new Intent(this, AddCounterActivity.class);
        startActivityForResult(intent, IntentConstants.ADD_COUNTER_INTENT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == IntentConstants.ADD_COUNTER_INTENT_RESULT) {
            String counterTitle = data.getStringExtra(IntentConstants.INTENT_COUNTER_TITLE);
            Integer counterInitialValue = data.getIntExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, 0);
            Date counterDate = new Date(data.getLongExtra(IntentConstants.INTENT_COUNTER_DATE, 0));
            String counterComment = data.getStringExtra(IntentConstants.INTENT_COUNTER_COMMENT);
            Counter counter = new Counter(counterTitle, counterDate, counterInitialValue, counterComment);
            counterList.add(counter);
        }
    }
}
