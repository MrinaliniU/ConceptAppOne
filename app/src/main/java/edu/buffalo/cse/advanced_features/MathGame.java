package edu.buffalo.cse.advanced_features;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MathGame extends AppCompatActivity {

    TextView question;
    int questionCounter;
    TextView timer;
    boolean gameStarted = false;
    TextView questionCount;
    Button optionOne;
    Button optionTwo;
    Button optionThree;
    Button optionFour;
    Button mainButton;
    int answer;
    int correctAnswerCount;
    int locationOfAnswer;
    ArrayList<Integer> allAnswers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);
        questionCount = findViewById(R.id.numberofQs);
        correctAnswerCount =0;
        optionOne = findViewById(R.id.optionOne);
        optionTwo = findViewById(R.id.optionTwo);
        optionThree = findViewById(R.id.optionThree);
        optionFour = findViewById(R.id.optionFour);
        question = findViewById(R.id.question);
        mainButton = findViewById(R.id.mainButton);
        enableButton(false);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!gameStarted){
                    enableButton(true);
                    startTimer();
                    generateQuestion();
                    gameStarted = true;
                }else{
                    mainButton.setText("Start!");
                    question.setText("Get Ready!");
                    gameStarted = false;
                }
            }
        });
    }

    public void chooseAnswer(View view){
        if(String.valueOf(locationOfAnswer).equals(view.getTag())){
            mainButton.setText("Correct!");
            correctAnswerCount++;
            questionCounter++;
            questionCount.setText(correctAnswerCount+"/" + questionCounter);
        }else{
            mainButton.setText("Wrong!");
            questionCounter++;
            questionCount.setText(correctAnswerCount+"/" + questionCounter);
        }
        generateQuestion();
    }

    public void generateQuestion(){
        allAnswers = new ArrayList<>();
        Random rand = new Random();
        int rOne = rand.nextInt(101);
        int rTwo = rand.nextInt(101);
        locationOfAnswer = rand.nextInt(4);
        question.setText(rOne + "+" + rTwo);
        answer = rOne + rTwo;
        for(int i = 0;i < 4;i++){
            if(locationOfAnswer == i){
                allAnswers.add(answer);
            }else{
                int wrongAns = rand.nextInt(201);
                while(wrongAns == answer){
                    wrongAns = rand.nextInt(201);
                }
                allAnswers.add(wrongAns);
            }

        }
        optionOne.setText(String.valueOf(allAnswers.get(0)));
        optionTwo.setText(String.valueOf(allAnswers.get(1)));
        optionThree.setText(String.valueOf(allAnswers.get(2)));
        optionFour.setText(String.valueOf(allAnswers.get(3)));
    }

    public void startTimer(){
        new CountDownTimer(30000,1000){
            public void onTick(long millisecondsUntilDone){
                timer = findViewById(R.id.timerMath);
                timer.setText(new SimpleDateFormat("ss").format(new Date( millisecondsUntilDone))+"s");

            }
            public void onFinish(){
                if(correctAnswerCount >= 15){
                    question.setText("You are a Genius!");

                }else{
                    question.setText("You Got " + correctAnswerCount + " Correct!");
                }
                mainButton.setText("Reset!");
                correctAnswerCount = 0;
                questionCounter=0;
                questionCount.setText(correctAnswerCount+"/" + questionCounter);
                enableButton(false);
            }
        }.start();
    }

    public void enableButton(boolean set){
        optionOne.setEnabled(set);
        optionTwo.setEnabled(set);
        optionThree.setEnabled(set);
        optionFour.setEnabled(set);
    }
}
