package com.example.quizoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button move = findViewById(R.id.button2);
        Button go = findViewById(R.id.button);
        Name = findViewById(R.id.editTextText);

        go.setOnClickListener(v -> {

            String NAME = Name.getText().toString();
            if(TextUtils.isEmpty(Name.getText().toString())){
                Toast.makeText(MainActivity.this, "Enter your Name", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent=new Intent(MainActivity.this,QuestionAnswer.class);
                intent.putExtra("name", NAME);
                startActivity(intent);
            }

        });

        move.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,HelpPageActivity.class);
            startActivity(intent);
        });


    }


}