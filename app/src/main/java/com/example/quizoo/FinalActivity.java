package com.example.quizoo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    TextView Correct, Wrong, Final;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Button Res = findViewById(R.id.resBtn);
        Correct = findViewById(R.id.c_ans);
        Wrong = findViewById(R.id.w_ans);
        Final = findViewById(R.id.f_ans);

        int CorrectAnswer = getIntent().getIntExtra("Correct",0);
        int WrongAnswer = getIntent().getIntExtra("Wrong",0);
        int FinalScore = getIntent().getIntExtra("Final",0);

        Correct.setText("Correct Answers : "+ String.valueOf(CorrectAnswer));
        Wrong.setText("Wrong Answers : "+ String.valueOf(WrongAnswer));
        Final.setText("Final Score : "+ String.valueOf(FinalScore));

        Res.setOnClickListener(v -> {
            Intent intent = new Intent(FinalActivity.this , MainActivity.class);
            startActivity(intent);
        });


    }
}