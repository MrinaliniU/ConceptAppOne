package edu.buffalo.cse.advanced_features;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PandaTimer extends AppCompatActivity {
    TextView _tv;
    Long progressValue;
    Button go;
    boolean isCounterActive = false;
    SeekBar seek;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_timer);
        _tv = findViewById(R.id.countDown);
        go = findViewById(R.id.goButton);
        //on click of a button timer will read progress of the seek bar = progressValue and start the timer.
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCounterActive =true;
                seek.setEnabled(false);
                startTimer(progressValue);
            }
        });
        seek = findViewById(R.id.seekBar2);
        seek.setMax(20);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                go.setText("Go!");
                //on change of progress, text view value will change also progressValue will be updated.
                setTimer(progress * 10000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void startTimer(long mills){
        timer = new CountDownTimer(mills,1000){
            public void onTick(long millisecondsUntilDone){
                _tv.setText(new SimpleDateFormat("mm:ss").format(new Date( millisecondsUntilDone)));
            }
            public void onFinish(){
                go.setText("Finish!");
                /*
                Here the getApplicationContext() is used instead of "this" keyword. Because "this" keyword here
                would refer to CountDownTimer.
                 */
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.timerdone);
                mp.start();
                isCounterActive =false;
                seek.setEnabled(true);
            }

        }.start();
    }

    public void setTimer(long millisecondsUntilDone){
        progressValue = millisecondsUntilDone;
        _tv.setText(new SimpleDateFormat("mm:ss").format(new Date( millisecondsUntilDone)));
    }
}
