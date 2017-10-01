package com.ualberta.kevin.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class ViewCounterActivity extends AppCompatActivity {

    Counter counter;

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

        counter = new Counter(counterTitle, counterDate, counterInitialValue, counterComment);
        counter.setCurrentValue(counterCurrentValue);

        TextView titleView = (TextView)findViewById(R.id.viewCounterTitle);
        titleView.setText(counterTitle);
        TextView initialValueView = (TextView)findViewById(R.id.viewCounterInitialValue);
        initialValueView.setText(initialValueView.toString());
        TextView currentValueView = (TextView)findViewById(R.id.viewCounterCurrentValue);
        currentValueView.setText(counterCurrentValue.toString());
        TextView commentView = (TextView)findViewById(R.id.viewCounterComment);
        commentView.setText(counterComment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra(IntentConstants.INTENT_COUNTER_TITLE, counter.getName());
        intent.putExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, counter.getInitialValue());
        intent.putExtra(IntentConstants.INTENT_COUNTER_DATE, counter.getDate().getTime());
        intent.putExtra(IntentConstants.INTENT_COUNTER_COMMENT, counter.getComment());
        setResult(IntentConstants.EDIT_COUNTER_INTENT_RESPONSE, intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent();
        intent.putExtra(IntentConstants.INTENT_COUNTER_TITLE, counter.getName());
        intent.putExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, counter.getInitialValue());
        intent.putExtra(IntentConstants.INTENT_COUNTER_DATE, counter.getDate().getTime());
        intent.putExtra(IntentConstants.INTENT_COUNTER_COMMENT, counter.getComment());
        setResult(IntentConstants.EDIT_COUNTER_INTENT_RESPONSE, intent);
        finish();
    }

    public void editCounter(View v) {

    }

    public void resetCounter(View v) {

    }

    public void deleteCounter(View v) {

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
