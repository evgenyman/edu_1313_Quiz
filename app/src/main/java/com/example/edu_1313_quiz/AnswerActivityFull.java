package com.example.edu_1313_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AnswerActivityFull extends AppCompatActivity {

//    private boolean isAnswerTrue;
    private String answerText;
    private TextView answerTextViewFull;
    private Button buttonBack;

    ArrayList<String> resultArray;
    int resume_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_full);

        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnswerActivityFull.this, MainActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        resultArray = getIntent().getStringArrayListExtra("answer");
        answerText = "";
        resume_int = getIntent().getIntExtra("answer_resume",0);

        for (int i = 0; i < resultArray.size(); i++) {
            answerText = answerText+resultArray.get(i)+"\n";
        }
        answerText = answerText+"Правильных ответов: "+resume_int;
        answerTextViewFull = findViewById(R.id.answerTextViewFull);
        answerTextViewFull.setText(answerText);

        /*isAnswerTrue = getIntent().getBooleanExtra("answer",true);
        answerTextViewFull = findViewById(R.id.answerTextView);
        answerTextViewFull.setText(isAnswerTrue?R.string.yas:R.string.no);*/
    }
}