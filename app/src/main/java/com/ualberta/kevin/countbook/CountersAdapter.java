package com.ualberta.kevin.countbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by weixiang on 10/1/17.
 */

class CountersAdapter extends ArrayAdapter<Counter> {
    public CountersAdapter(Context context, ArrayList<Counter> counters) {
        super(context, 0, counters);
    }

    private void putCounterDataIntoIntent(Counter counter, int position, Intent intent) {
        intent.putExtra(IntentConstants.INTENT_COUNTER_INDEX, position);
        intent.putExtra(IntentConstants.INTENT_COUNTER_TITLE, counter.getName());
        intent.putExtra(IntentConstants.INTENT_COUNTER_CURRENT_VALUE, counter.getCurrentValue());
        intent.putExtra(IntentConstants.INTENT_COUNTER_INITIAL_VALUE, counter.getInitialValue());
        intent.putExtra(IntentConstants.INTENT_COUNTER_DATE, counter.getDate().getTime());
        intent.putExtra(IntentConstants.INTENT_COUNTER_COMMENT, counter.getComment());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Counter counter = this.getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_counter, parent, false);
        }
        // Lookup view for data population
        TextView titleView = (TextView) convertView.findViewById(R.id.counterListItemTitle);
        TextView currentValueView = (TextView) convertView.findViewById(R.id.counterListItemCurrentValue);
        TextView dateView = (TextView) convertView.findViewById(R.id.counterListItemDate);
        Button viewButton = (Button) convertView.findViewById(R.id.counterListItemViewButton);
        Button editButton = (Button) convertView.findViewById(R.id.counterListItemEditButton);
        // Populate the data into the template view using the data object
        titleView.setText(counter.getName());
        currentValueView.setText(counter.getCurrentValue().toString());
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
        dateView.setText(DATE_FORMAT.format(counter.getDate()).toString());

        View.OnClickListener viewListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ViewCounterActivity.class);
                putCounterDataIntoIntent(counter, position, intent);
                ((Activity)getContext()).startActivityForResult(intent, IntentConstants.EDIT_COUNTER_INTENT_REQUEST);
            }
        };

        View.OnClickListener editListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditCounterActivity.class);
                putCounterDataIntoIntent(counter, position, intent);
                ((Activity)getContext()).startActivityForResult(intent, IntentConstants.EDIT_COUNTER_INTENT_REQUEST);
            }
        };

        viewButton.setOnClickListener(viewListener);
        editButton.setOnClickListener(editListener);

        // Return the completed view to render on screen
        return convertView;
    }
}