/**
 * NewCounterActivity Class
 *
 * Copyright 2017 Kevin Wang
 *
 * @author weixiang
 * @version 1.0
 * @created 2017-09-27
 */

package com.ualberta.kevin.countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * NewCounterActivity controls a form used for creating a
 * new counter.
 */

public class NewCounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);
    }

    /**
     * Checks if all data in the form is valid. Then, finishes the activity
     * and sends data back to the previous activity.
     * @param v
     */
    public void saveCounter(View v) {
        String counterTitle = ((EditText) findViewById(R.id.counterTitle)).getText().toString();
        Date date = new Date();
        String initialValueString = ((EditText) findViewById(R.id.counterInitialValue)).getText().toString();
        String comment = ((EditText) findViewById(R.id.counterComment)).getText().toString();

        if (counterTitle.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter a title", Toast.LENGTH_SHORT).show();
        } else if (initialValueString.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter an initial value", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(initialValueString) < 0) {
            Toast.makeText(getApplicationContext(), "The initial value must be positive", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent();
            intent.putExtra(IntentConstants.INTENT_COUNTER_TITLE, counterTitle);
            intent.putExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, Integer.parseInt(initialValueString));
            intent.putExtra(IntentConstants.INTENT_COUNTER_DATE, date.getTime());
            intent.putExtra(IntentConstants.INTENT_COUNTER_COMMENT, comment);
            setResult(IntentConstants.ADD_COUNTER_INTENT_RESULT, intent);
            finish();
        }
    }

}
