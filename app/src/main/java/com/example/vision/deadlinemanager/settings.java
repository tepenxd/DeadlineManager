package com.example.vision.deadlinemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class settings extends AppCompatActivity {
    String items[] = new String[]{"Notification settings", "Reset", "Backup Data", "About"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ListView listView = (ListView)findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), NotificationSettings.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {

                }

                if (position == 2) {

                }

                if (position == 3) {
                    Intent myIntent = new Intent(view.getContext(), AboutActivity.class);
                    startActivityForResult(myIntent, 0);
                }

            }
        });

    }
}
