package com.ualberta.kevin.countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class AddCounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);
    }

    public void saveCounter(View v) {
        String counterTitle = ((EditText) findViewById(R.id.counterTitle)).getText().toString();
        Date date = new Date();
        String initialValueString = ((EditText) findViewById(R.id.counterInitialValue)).getText().toString();
        String comment = ((EditText) findViewById(R.id.counterComment)).getText().toString();

        if (counterTitle.equals("")) {

        } else if (initialValueString.equals("")) {

        } else {
            Intent intent = new Intent();
            intent.putExtra(IntentConstants.INTENT_COUNTER_TITLE, counterTitle);
            intent.putExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, Integer.parseInt(initialValueString));
            intent.putExtra(IntentConstants.INTENT_COUNTER_DATE, date.getTime());
            intent.putExtra(IntentConstants.INTENT_COUNTER_COMMENT, comment);
            setResult(IntentConstants.ADD_COUNTER_INTENT_RESULT);
            finish();
        }
    }

}
