package com.darrenmleith.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView questionTextView;
    TextView scoreTextView;
    TextView userFeedBack;
    Button playAgainButton;
    Button startGameButton;
    Button buttonAnsOne;
    Button buttonAnsTwo;
    Button buttonAnsThree;
    Button buttonAnsFour;
    int correctAnswer;
    int gameScore = 0;
    CountDownTimer countDownTimer;
    Boolean gameInProgress = false;
    int correctAnswerPosition;
    ArrayList<Integer> fourPossibleAnswers;
    int noOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.textViewTimer);
        questionTextView = findViewById(R.id.textViewQuestion);
        scoreTextView = findViewById(R.id.textViewScore);
        userFeedBack = findViewById(R.id.textViewQuestionResult);
        playAgainButton = findViewById(R.id.buttonPlayAgain);
        startGameButton = findViewById(R.id.buttonStartGame);
        buttonAnsOne = findViewById(R.id.buttonAnsOne);
        buttonAnsTwo = findViewById(R.id.buttonAnsTwo);
        buttonAnsThree = findViewById(R.id.buttonAnsThree);
        buttonAnsFour = findViewById(R.id.buttonAnsFour);


        //initially the screen is blank except for the playGame button
        timerTextView.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        userFeedBack.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        startGameButton.setVisibility(View.VISIBLE);
        buttonAnsOne.setVisibility(View.INVISIBLE);
        buttonAnsTwo.setVisibility(View.INVISIBLE);
        buttonAnsThree.setVisibility(View.INVISIBLE);
        buttonAnsFour.setVisibility(View.INVISIBLE);
    }

    public void startGame(View view) {
        gameInitializer();
    }

    private void gameInitializer() {
        gameInProgress = true;
        timerTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        startGameButton.setVisibility(View.INVISIBLE);
        buttonAnsOne.setVisibility(View.VISIBLE);
        buttonAnsTwo.setVisibility(View.VISIBLE);
        buttonAnsThree.setVisibility(View.VISIBLE);
        buttonAnsFour.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        timerTextView.setText("30s");
        gameScore = 0;
        noOfQuestions = 0;
        scoreTextView.setText(Integer.toString(gameScore));
        userFeedBack.setText("");
        startTimer();
        playingAlgorithm();

    }

    public void playingAlgorithm() {
        if (gameInProgress) {
            int variableOne = randomNumber();
            int variableTwo = randomNumber();
            questionTextView.setText("" + variableOne + " + " + variableTwo);
            correctAnswer = variableOne + variableTwo;

            fourPossibleAnswers = new ArrayList<>();
            correctAnswerPosition = (int) (Math.random() * 4);
            for (int i = 0; i < 4 ; i++) {
                if (i == correctAnswerPosition) {
                    fourPossibleAnswers.add(correctAnswer);
                } else {
                    int wrongAnswer = randomNumber();
                    while (wrongAnswer == correctAnswer) {
                        wrongAnswer = randomNumber();
                    }
                    fourPossibleAnswers.add(wrongAnswer);
                }
            }
            buttonAnsOne.setText(Integer.toString(fourPossibleAnswers.get(0)));
            buttonAnsTwo.setText(Integer.toString(fourPossibleAnswers.get(1)));
            buttonAnsThree.setText(Integer.toString(fourPossibleAnswers.get(2)));
            buttonAnsFour.setText(Integer.toString(fourPossibleAnswers.get(3)));
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long l) {
                Log.i("Seconds left", String.valueOf(l / 1000));
                timerTextView.setText("" + String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                userFeedBack.setText("Game Over!");
                playAgainButton.setVisibility(View.VISIBLE);
                gameInProgress = false;
            }
        }.start();
    }

    public void checkAnswer(View view) {
        if (gameInProgress) {
            noOfQuestions++;
            Button clickedButton = (Button) view;
            if (clickedButton.getTag().toString().equals(Integer.toString(correctAnswerPosition))) {
                userFeedBack.setText("Correct !");
                userFeedBack.setTextColor(Color.GREEN);
                userFeedBack.setVisibility(View.VISIBLE);
                gameScore++;
                scoreTextView.setText(gameScore + "/" + noOfQuestions);
                playingAlgorithm();
            } else {
                userFeedBack.setText("Wrong :-/");
                userFeedBack.setTextColor(Color.RED);
                userFeedBack.setVisibility(View.VISIBLE);
                scoreTextView.setText(gameScore + "/" + noOfQuestions);
                playingAlgorithm();
            }
        }
    }

    private int randomNumber() {
        Random r = new Random();
        return r.nextInt(50) + 1;
    }

    public void playAgain(View view) {
        gameInitializer();
    }
}
