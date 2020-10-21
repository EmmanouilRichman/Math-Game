package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Views
    TextView timerView;
    TextView problemView;
    TextView percentageView;
    TextView correctView;
    Button gameStartStop;

    //Timer
    CountDownTimer timer;

    //Needed to change game state
    boolean playing = false;

    //Hold on to choices per problem
    ArrayList<Integer> answers = new ArrayList<>();

    //Variables for keeping track of score;
    int numberOfProblems;
    int correctAnswers;
    int wrongAnswers;
    int numberOfChoices = 4;
    int totalTime;

    public void gameState(View view){
        if(playing == false){
            numberOfProblems = 0;
            correctAnswers = 0;
            wrongAnswers = 0;
            totalTime = 30;
            startGame();
        }
        else{
            stop();
        }

    }


    public void startGame(){

      playing = true;
       percentageView.setText("0%");
       gameStartStop.setVisibility(View.INVISIBLE);
       gameStartStop.setClickable(false);

       timer = new CountDownTimer(30000, 1000) {
           @Override
           public void onTick(long millisUntilFinished) {
               timerView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
               totalTime -= 1;
           }

           @Override
           public void onFinish() {
               stop();
           }
       }.start();


    }

    public void stop(){
       playing = false;
       gameStartStop.setText("Play Again?");
       gameStartStop.setClickable(true);
       gameStartStop.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerView = (TextView) findViewById(R.id.timerView);
        problemView = (TextView) findViewById(R.id.problemView);
        percentageView = (TextView) findViewById(R.id.percentageView);
        correctView = (TextView) findViewById(R.id.correctView);
        gameStartStop = (Button) findViewById(R.id.gameStartStop);
        gameStartStop.setText("Play");
    }
}