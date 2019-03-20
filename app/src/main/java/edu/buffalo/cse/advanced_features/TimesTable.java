package edu.buffalo.cse.advanced_features;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class TimesTable extends AppCompatActivity {
    ArrayList<Integer> one;
    ListView listView;
    ArrayAdapter<Integer> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_table);
        listView = findViewById(R.id.listView2);
        SeekBar seek = findViewById(R.id.seekBar);
        //without set min the seek starts from 0 to 100, mow it is 1 to 99.
        seek.setMin(1);
        //set seek to 100
        seek.setMax(101);
        one = new ArrayList<>(asList(1,2,3,4,5,6,7,8,9,10));
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,one);
        listView.setAdapter(adapter);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                multiply(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void multiply(int progress){
        ArrayList<Integer> two = new ArrayList<>() ;
     for(int i = 0;i<one.size();i++){
         two.add(one.get(i) * progress);
     }
     /*
     I tried to add the below two lines directly inside onProgressChanged but "this"
     in ArrayAdapter would then refer to Seekbar and not Activity itself. So added a
     Separate method that handles setting ArrayAdapter.
      */
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,two);
        listView.setAdapter(adapter);
    }
}
