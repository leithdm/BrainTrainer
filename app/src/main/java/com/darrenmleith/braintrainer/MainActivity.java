package com.darrenmleith.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    TextView question;
    TextView score;
    TextView questionResult;
    Button playAgain;
    Button startGame;
    Button buttonAnsOne;
    Button buttonAnsTwo;
    Button buttonAnsThree;
    Button buttonAnsFour;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initially the screen is blank except for the playGame button
        timer = findViewById(R.id.textViewTimer);
        question = findViewById(R.id.textViewQuestion);
        score = findViewById(R.id.textViewScore);
        questionResult = findViewById(R.id.textViewQuestionResult);
        playAgain = findViewById(R.id.buttonPlayAgain);
        startGame = findViewById(R.id.buttonStartGame);
        buttonAnsOne = findViewById(R.id.buttonAnsOne);
        buttonAnsTwo = findViewById(R.id.buttonAnsTwo);
        buttonAnsThree = findViewById(R.id.buttonAnsThree);
        buttonAnsFour = findViewById(R.id.buttonAnsFour);

        timer.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        questionResult.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        startGame.setVisibility(View.VISIBLE);
        buttonAnsOne.setVisibility(View.INVISIBLE);
        buttonAnsTwo.setVisibility(View.INVISIBLE);
        buttonAnsThree.setVisibility(View.INVISIBLE);
        buttonAnsFour.setVisibility(View.INVISIBLE);
    }

    public void startGame(View view) {
        timer.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        startGame.setVisibility(View.INVISIBLE);
        buttonAnsOne.setVisibility(View.VISIBLE);
        buttonAnsTwo.setVisibility(View.VISIBLE);
        buttonAnsThree.setVisibility(View.VISIBLE);
        buttonAnsFour.setVisibility(View.VISIBLE);
    }

    public void playAgain (View view) {

    }

}
