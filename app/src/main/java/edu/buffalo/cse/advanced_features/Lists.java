package edu.buffalo.cse.advanced_features;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class Lists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        ListView myListView = findViewById(R.id.listView);
        /*
        A fun way to add multiple strings to ArrayList instead of using multiple add()s.
         */
        final ArrayList<String> toDo = new ArrayList<String>(asList("Be Mindful","Read Change in Emotion","Choose Your Company",
                "Work Towards Your Goal","Be Kind","Learn to Forgive"));
       /*
       android.R.layout.simple_list_item_1 is a built in functionality to display simple list view.
        */
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,toDo);
        myListView.setAdapter(adapter);
        /*
        See how adapter.getItem(position) gets the item that is currently clicked.
        */
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText( getApplicationContext(),adapter.getItem(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
