package com.ualberta.kevin.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class EditCounterActivity extends AppCompatActivity {

    private Counter counter;
    private int counterIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        Intent intent = getIntent();
        String counterTitle = intent.getStringExtra(IntentConstants.INTENT_COUNTER_TITLE);
        Integer counterInitialValue = intent.getIntExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, 0);
        Date counterDate = new Date(intent.getLongExtra(IntentConstants.INTENT_COUNTER_DATE, 0));
        String counterComment = intent.getStringExtra(IntentConstants.INTENT_COUNTER_COMMENT);
        Integer counterCurrentValue = intent.getIntExtra(IntentConstants.INTENT_COUNTER_CURRENT_VALUE, 0);
        counterIndex = intent.getIntExtra(IntentConstants.INTENT_COUNTER_INDEX, 0);

        counter = new Counter(counterTitle, counterDate, counterInitialValue, counterComment);
        counter.setCurrentValue(counterCurrentValue);

        TextView titleView = (TextView)findViewById(R.id.editCounterTitle);
        titleView.setText(counterTitle);
        TextView initialValueView = (TextView)findViewById(R.id.editCounterInitialValue);
        initialValueView.setText(counterInitialValue.toString());
        TextView currentValueView = (TextView)findViewById(R.id.editCounterCurrentValue);
        currentValueView.setText(counterCurrentValue.toString());
        TextView commentView = (TextView)findViewById(R.id.editCounterComment);
        commentView.setText(counterComment);
    }

    public void saveEdits(View v) {
        String counterTitle = ((EditText) findViewById(R.id.editCounterTitle)).getText().toString();
        Date date = new Date();
        String initialValueString = ((EditText) findViewById(R.id.editCounterInitialValue)).getText().toString();
        String currentValueString = ((EditText) findViewById(R.id.editCounterCurrentValue)).getText().toString();
        String comment = ((EditText) findViewById(R.id.editCounterComment)).getText().toString();

        if (counterTitle.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter a title", Toast.LENGTH_SHORT).show();
        } else if (initialValueString.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter an initial value", Toast.LENGTH_SHORT).show();
        } else if (currentValueString.equals("")) {
           Toast.makeText(getApplicationContext(), "Please enter a current value", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(initialValueString) < 0) {
            Toast.makeText(getApplicationContext(), "The initial value must be positive", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(currentValueString) < 0) {
            Toast.makeText(getApplicationContext(), "The current value must be positive", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent();
            intent.putExtra(IntentConstants.INTENT_COUNTER_TITLE, counterTitle);
            intent.putExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, Integer.parseInt(initialValueString));
            intent.putExtra(IntentConstants.INTENT_COUNTER_CURRENT_VALUE, counter.getCurrentValue());
            intent.putExtra(IntentConstants.INTENT_COUNTER_DATE, date.getTime());
            intent.putExtra(IntentConstants.INTENT_COUNTER_COMMENT, comment);
            intent.putExtra(IntentConstants.INTENT_COUNTER_INDEX, counterIndex);
            setResult(IntentConstants.EDIT_COUNTER_INTENT_RESPONSE, intent);
            finish();
        }
    }
}
























