package com.example.quizoo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class QuestionAnswer extends AppCompatActivity {

    private TextView tvQues, tvScore, Ques;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btn_nxt;
    private RadioGroup radioGroup;
    private int totalQuestions;
    private int qCounter = 0;
    private ColorStateList dfRbColor;
    private boolean answered;
    private BandA currentQuestion;
    private List<BandA> questionsList;

    // Create a variable to track the score
    private int score = 0;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);

        TextView greetingTextView = findViewById(R.id.textView7);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            if (name != null) {
                greetingTextView.setText("Hello! " + name);
            }
        }


        Button quit = findViewById(R.id.button4);
        quit.setOnClickListener(v -> {
            Intent intent1 = new Intent(QuestionAnswer.this, FinalActivity.class);
            startActivity(intent1);
        });

        questionsList = new ArrayList<>();
        Ques = findViewById(R.id.questions);
        tvQues = findViewById(R.id.questionNo);
        tvScore = findViewById(R.id.textView10);
        radioGroup = findViewById(R.id.RadioGroup);
        rb1 = findViewById(R.id.Btn1);
        rb2 = findViewById(R.id.Btn2);
        rb3 = findViewById(R.id.Btn3);
        rb4 = findViewById(R.id.Btn4);
        btn_nxt = findViewById(R.id.button3);
        dfRbColor = rb1.getTextColors();

        addQuestions();
        totalQuestions = questionsList.size();
        showNextQuestion();

        btn_nxt.setOnClickListener(v -> {
            if (!answered) {
                if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                    checkAnswer();
                } else {
                    Toast.makeText(QuestionAnswer.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            } else {
                showNextQuestion();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) + 1;
        if (answerNo == currentQuestion.getCorrectAnswer()) {
            // Increment the score if the answer is correct
            score++;
            tvScore.setText("Your Score: " + score);
        }
        // Highlight the correct answer in green
        highlightCorrectAnswer();
        if (qCounter < totalQuestions) {
            btn_nxt.setText("NEXT QUESTION");
        } else {
            btn_nxt.setText("FINISH");
        }
    }

    private void highlightCorrectAnswer() {
        switch (currentQuestion.getCorrectAnswer()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        rb4.setTextColor(dfRbColor);

        if (qCounter < totalQuestions) {
            currentQuestion = questionsList.get(qCounter);
            Ques.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            qCounter++;
            tvQues.setText("Question: " + qCounter + "/" + totalQuestions);
            answered = false;
            btn_nxt.setText("CHECK ANSWER");
        } else {

            Intent intent = new Intent(QuestionAnswer.this, FinalActivity.class);
            intent.putExtra("Correct", score);
            intent.putExtra("Wrong", totalQuestions-score);
            intent.putExtra("Final", score);
            startActivity(intent);
            finish();
        }
    }

    private void addQuestions() {
        questionsList.add(new BandA("Which method can be defined only once in a program?", "finalize method", "main method", "static method", "private method", 2));
        questionsList.add(new BandA("Which keyword is used by a method to refer to the current object that invoked it?", "import", "this", "catch", "abstract", 2));
        questionsList.add(new BandA("Which of these access specifiers can be used for an interface?", "public", "protected", "private", "All of the mentioned", 1));
        questionsList.add(new BandA("Which of the following is the correct way of importing an entire package ‘pkg’?", "Import pkg.", "import pkg.*", "Import pkg.*", "import pkg.", 2));
        questionsList.add(new BandA("What is the return type of Constructors?", "int", "float", "void", "None of the mentioned", 4));
    }
}