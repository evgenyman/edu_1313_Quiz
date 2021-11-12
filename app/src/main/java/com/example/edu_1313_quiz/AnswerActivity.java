package com.example.edu_1313_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class AnswerActivity extends AppCompatActivity {

    private boolean isAnswerTrue;
    private TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        isAnswerTrue = getIntent().getBooleanExtra("answer",true);
        answerTextView = findViewById(R.id.answerTextView);
        answerTextView.setText(isAnswerTrue?R.string.yas:R.string.no);

        /* if (isAnswerTrue)
            answerTextView.setText(R.string.yas);
        else
            answerTextView.setText(R.string.no);*/

    }
}