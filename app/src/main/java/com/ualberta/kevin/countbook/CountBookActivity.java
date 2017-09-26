package com.ualberta.kevin.countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class CountBookActivity extends AppCompatActivity {

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

        counterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Counter counter = counterList.get(position);
                Intent intent = new Intent(CountBookActivity.this, ViewCounterActivity.class);
                intent.putExtra(IntentConstants.INTENT_COUNTER_TITLE, counter.getName());
                intent.putExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, counter.getInitialValue());
                intent.putExtra(IntentConstants.INTENT_COUNTER_DATE, counter.getDate().getTime());
                intent.putExtra(IntentConstants.INTENT_COUNTER_COMMENT, counter.getComment());
                intent.putExtra(IntentConstants.INTENT_COUNTER_CURRENT_VALUE, counter.getCurrentValue());
                startActivityForResult(intent, IntentConstants.EDIT_COUNTER_INTENT_REQUEST);
            }
        });
    }

    public void addCounter(View v) {
        Intent intent = new Intent(this, NewCounterActivity.class);
        startActivityForResult(intent, IntentConstants.ADD_COUNTER_INTENT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == IntentConstants.DELETE_COUNTER_INTENT_RESPONSE) {
            int counterIndex = data.getIntExtra(IntentConstants.INTENT_COUNTER_INDEX, 0);
            Counter counter = counterList.get(counterIndex);
            counterArrayAdapter.remove(counter);
            return;
        }
        String counterTitle = data.getStringExtra(IntentConstants.INTENT_COUNTER_TITLE);
        Integer counterInitialValue = data.getIntExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, 0);
        Date counterDate = new Date(data.getLongExtra(IntentConstants.INTENT_COUNTER_DATE, 0));
        String counterComment = data.getStringExtra(IntentConstants.INTENT_COUNTER_COMMENT);
        if (resultCode == IntentConstants.ADD_COUNTER_INTENT_RESULT) {
            Counter counter = new Counter(counterTitle, counterDate, counterInitialValue, counterComment);
            counterArrayAdapter.add(counter);
        } else if (resultCode == IntentConstants.EDIT_COUNTER_INTENT_RESPONSE) {
            Integer counterCurrentValue = data.getIntExtra(IntentConstants.INTENT_COUNTER_CURRENT_VALUE, 0);
            int counterIndex = data.getIntExtra(IntentConstants.INTENT_COUNTER_INDEX, 0);
            Counter counter = counterList.get(counterIndex);
            counter.setName(counterTitle);
            counter.setInitialValue(counterInitialValue);
            counter.setDate(counterDate);
            counter.setCurrentValue(counterCurrentValue);
            counter.setComment(counterComment);
        }
    }

    private class CountersAdapter extends ArrayAdapter<Counter> {
        public CountersAdapter(Context context, ArrayList<Counter> counters) {
            super(context, 0, counters);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Counter counter = counterList.get(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_counter, parent, false);
            }
            // Lookup view for data population
            TextView titleView = (TextView) convertView.findViewById(R.id.counterListItemTitle);
            TextView currentValueView = (TextView) convertView.findViewById(R.id.counterListItemCurrentValue);
            // Populate the data into the template view using the data object
            titleView.setText(counter.getName());
            currentValueView.setText(counter.getCurrentValue().toString());
            // Return the completed view to render on screen
            return convertView;
        }
    }
}
