package com.darrenmleith.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

    ConstraintLayout mainConstraintLayout;
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
    CountDownTimer countDownTimer;
    Boolean gameInProgress = false;
    ArrayList<Integer> possibleAnswers;
    int correctAnswerPosition;
    int gameScore = 0;
    int noOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameButton = findViewById(R.id.buttonStartGame);
        startGameButton.setVisibility(View.VISIBLE);
        mainConstraintLayout = findViewById(R.id.mainConstraintLayout);
        mainConstraintLayout.setVisibility(View.INVISIBLE);


        timerTextView = findViewById(R.id.textViewTimer);
        scoreTextView = findViewById(R.id.textViewScore);
        userFeedBack = findViewById(R.id.textViewQuestionResult);
        buttonAnsOne = findViewById(R.id.buttonAnsOne);
        buttonAnsTwo = findViewById(R.id.buttonAnsTwo);
        buttonAnsThree = findViewById(R.id.buttonAnsThree);
        buttonAnsFour = findViewById(R.id.buttonAnsFour);
        questionTextView = findViewById(R.id.textViewQuestion);
        playAgainButton = findViewById(R.id.buttonPlayAgain);
    }

    public void startGame(View view) {
        gameInitializer();
    }

    public void playAgain(View view) {
        gameInitializer();
    }

    private void gameInitializer() {
        startGameButton.setVisibility(View.INVISIBLE);
        mainConstraintLayout.setVisibility(View.VISIBLE);
        gameInProgress = true;
        timerTextView.setText("30s");
        gameScore = 0;
        noOfQuestions = 0;
        scoreTextView.setText(Integer.toString(gameScore));
        playAgainButton.setVisibility(View.INVISIBLE);
        userFeedBack.setText("");
        startTimer();
        playingAlgorithm();
    }

    public void playingAlgorithm() {
        if (gameInProgress) {
            int variableOne = randomNumber();
            int variableTwo = randomNumber();
            questionTextView.setText("" + variableOne + " + " + variableTwo);
            int correctAnswer = variableOne + variableTwo;

            possibleAnswers = new ArrayList<>();
            correctAnswerPosition = (int) (Math.random() * 4);
            for (int i = 0; i < 4; i++) {
                if (i == correctAnswerPosition) {
                    possibleAnswers.add(correctAnswer);
                } else {
                    int wrongAnswer = randomNumber();
                    while (wrongAnswer == correctAnswer) {
                        wrongAnswer = randomNumber();
                    }
                    possibleAnswers.add(wrongAnswer);
                }
            }
            buttonAnsOne.setText(Integer.toString(possibleAnswers.get(0)));
            buttonAnsTwo.setText(Integer.toString(possibleAnswers.get(1)));
            buttonAnsThree.setText(Integer.toString(possibleAnswers.get(2)));
            buttonAnsFour.setText(Integer.toString(possibleAnswers.get(3)));
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
                userFeedBack.setTextColor(Color.BLACK);
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
                userFeedBack.setTextColor(Color.BLUE);
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
}
