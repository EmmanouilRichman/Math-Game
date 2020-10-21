package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Views
    TextView timerView;
    TextView problemView;
    TextView percentageView;
    TextView correctView;
    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView answer4;
    Button gameStartStop;

    //Timer
    CountDownTimer timer;

    //Needed to change game state
    boolean playing = false;

    //Hold on to all answers
    ArrayList<TextView> allViews = new ArrayList<>();


    //Variables for keeping track of score;
    int numberOfProblems;
    int correctAnswers;
    int wrongAnswers;
    int totalTime;
    int correctAnswer;
    Random randomNum;
    String actualOperation = "";

    public void gameState(View view){
        if(playing == false){
           numberOfProblems = 0;
           correctAnswers = 0;
           wrongAnswers = 0;
           totalTime = 30;
           startGame();
        }
        else
        {
            stop();
        }
    }

    public void startGame(){
        playing = true;
        gameStartStop.setVisibility(View.INVISIBLE);
        gameStartStop.setClickable(false);
        setUp();

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
       actualOperation = "";
       correctAnswer = 0;
       gameStartStop.setText("Play Again?");
       gameStartStop.setClickable(true);
       gameStartStop.setVisibility(View.VISIBLE);
    }

    public void setUp() {
        int firstNum = randomNum.nextInt(20 - 0) + 1;
        int secondNum = randomNum.nextInt(20 - 0) + 1;
        int findOperation = randomNum.nextInt(4 - 1) + 1;

        switch (findOperation)
        {
            case 1:
                actualOperation = "+";
                break;
            case 2:
                actualOperation = "-";
                break;
            default:
                actualOperation = "*";
                break;
        }
        correctAnswer = getAnswer(firstNum, secondNum);

        //Put correct answer into random view
        int answerLocation = randomNum.nextInt(4 - 0) + 0;
        allViews.get(answerLocation).setText(String.valueOf(correctAnswer));

        //Fill rest of answer views
        for(int i = 0; i < 4; i++){
            if(!allViews.get(i).getText().equals(String.valueOf(correctAnswer))){
                allViews.get(i).setText(String.valueOf(randomNum.nextInt(101 - 1) + 1));
            }
        }
        problemView.setText(String.valueOf(firstNum) + " " + actualOperation + " " + String.valueOf(secondNum));
    }

    public int getAnswer(int firstNum, int secondNum){
        int answer = 0;
        switch(actualOperation)
        {
            case "+":
                answer = firstNum + secondNum;
                break;
            case "-":
                answer = firstNum - secondNum;
                break;
            case "*":
                answer = firstNum * secondNum;
                break;
        }
        return answer;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerView = (TextView) findViewById(R.id.timerView);
        problemView = (TextView) findViewById(R.id.problemView);
        percentageView = (TextView) findViewById(R.id.percentageView);
        correctView = (TextView) findViewById(R.id.correctView);
        answer1 = (TextView) findViewById(R.id.answer1);
        answer2 = (TextView) findViewById(R.id.answer2);
        answer3 = (TextView) findViewById(R.id.answer3);
        answer4 = (TextView) findViewById(R.id.answer4);
        gameStartStop = (Button) findViewById(R.id.gameStartStop);
        randomNum = new Random();
        gameStartStop.setText("Play");
        percentageView.setText("0%");
        timerView.setText("0s");

        //adding answer views to array
        allViews.add(answer1);
        allViews.add(answer2);
        allViews.add(answer3);
        allViews.add(answer4);

    }
}