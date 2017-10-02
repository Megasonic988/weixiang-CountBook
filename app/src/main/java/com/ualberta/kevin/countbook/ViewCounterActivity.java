/**
 * ViewCounterActivity Class
 *
 * Copyright 2017 Kevin Wang
 *
 * @author weixiang
 * @version 1.0
 * @created 2017-09-27
 */

package com.ualberta.kevin.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ViewCounterActivity controls an interface used for viewing, resetting,
 * deleting, and changing the value of a single counter.
 */

public class ViewCounterActivity extends AppCompatActivity {

    private Counter counter;
    private int counterIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_counter);

        Intent intent = getIntent();
        String counterTitle = intent.getStringExtra(IntentConstants.INTENT_COUNTER_TITLE);
        Integer counterInitialValue = intent.getIntExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, 0);
        Date counterDate = new Date(intent.getLongExtra(IntentConstants.INTENT_COUNTER_DATE, 0));
        String counterComment = intent.getStringExtra(IntentConstants.INTENT_COUNTER_COMMENT);
        Integer counterCurrentValue = intent.getIntExtra(IntentConstants.INTENT_COUNTER_CURRENT_VALUE, 0);
        counterIndex = intent.getIntExtra(IntentConstants.INTENT_COUNTER_INDEX, 0);

        counter = new Counter(counterTitle, counterDate, counterInitialValue, counterComment);
        counter.setCurrentValue(counterCurrentValue);

        TextView titleView = (TextView)findViewById(R.id.viewCounterTitle);
        titleView.setText(counterTitle);
        TextView initialValueView = (TextView)findViewById(R.id.viewCounterInitialValue);
        initialValueView.setText("Initial Value: " + counterInitialValue.toString());
        TextView currentValueView = (TextView)findViewById(R.id.viewCounterCurrentValue);
        currentValueView.setText(counterCurrentValue.toString());
        TextView commentView = (TextView)findViewById(R.id.viewCounterComment);
        commentView.setText(counterComment);
        TextView dateView = (TextView)findViewById(R.id.viewCounterDate);
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
        dateView.setText("Last Updated: " + DATE_FORMAT.format(counter.getDate()).toString());
    }
    
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(IntentConstants.INTENT_COUNTER_TITLE, counter.getName());
        intent.putExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, counter.getInitialValue());
        intent.putExtra(IntentConstants.INTENT_COUNTER_CURRENT_VALUE, counter.getCurrentValue());
        intent.putExtra(IntentConstants.INTENT_COUNTER_COMMENT, counter.getComment());
        intent.putExtra(IntentConstants.INTENT_COUNTER_INDEX, counterIndex);
        setResult(IntentConstants.EDIT_COUNTER_INTENT_RESPONSE, intent);
        finish();
    }

    public void resetCounter(View v) {
        counter.setCurrentValue(counter.getInitialValue());
        TextView currentValueView = (TextView)findViewById(R.id.viewCounterCurrentValue);
        currentValueView.setText(counter.getCurrentValue().toString());
        Toast.makeText(getApplicationContext(), "Changed value back to initial value!", Toast.LENGTH_SHORT).show();
    }

    public void deleteCounter(View v) {
        Intent intent = new Intent();
        intent.putExtra(IntentConstants.INTENT_COUNTER_INDEX, counterIndex);
        setResult(IntentConstants.DELETE_COUNTER_INTENT_RESPONSE, intent);
        finish();
    }

    public void incrementCounter(View v) {
        counter.increment();
        TextView currentValueView = (TextView)findViewById(R.id.viewCounterCurrentValue);
        currentValueView.setText(counter.getCurrentValue().toString());
    }

    public void decrementCounter(View v) {
        counter.decrement();
        TextView currentValueView = (TextView)findViewById(R.id.viewCounterCurrentValue);
        currentValueView.setText(counter.getCurrentValue().toString());
    }
}
