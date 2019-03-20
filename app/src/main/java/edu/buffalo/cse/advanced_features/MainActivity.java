package edu.buffalo.cse.advanced_features;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
/*
The way the four buttons are arranged in this activity is a good example of Grid Layout.
Property like layout_column, layout_columnWeight will fit buttons correctly in a Grid
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickList(View view){
        Intent intent = new Intent(this, Lists.class);
        startActivity(intent);
    }

    public void onClickTable(View view){
        Intent intent = new Intent(this, TimesTable.class);
        startActivity(intent);
    }

    public void onClickTimer(View view){
        Intent intent = new Intent(this, PandaTimer.class);
        startActivity(intent);
    }

    public void onClickGame(View view){
        Intent intent = new Intent(this, MathGame.class);
        startActivity(intent);
    }
}
