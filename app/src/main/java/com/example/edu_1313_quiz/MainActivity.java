package com.example.edu_1313_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button yesBtn;  // "объявляем" переменную yesBtn
    private Button noBtn;
    private Button answerBtn;

    // создаем переменную 'textView' типа TextView, чтобы писать туда вопрос и отображать на экране
    private TextView textView;

    // создаем Массив элементов состоящий из наших "Вопросов к пользователю".
    // причем в данном конкретном случае - сразу заполняем его содержимым (элементами)
    Question[] questions = {
            new Question(R.string.question1,true),
            new Question(R.string.question2,true),
            new Question(R.string.question3,false),
            new Question(R.string.question4,false),
            new Question(R.string.question5,false)
    };

    int questionIndex = 0;  // мы создали переменную индекса массива, куда записали первое его значение - 0

    ArrayList<String> resultArray = new ArrayList();
    String choiсe;
    int resume_int = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SYSTEM_INF: ","Вызван метод onCreate()");    // выводим в Лог сообщение о вызванном методе onCreate()
        setContentView(R.layout.activity_main);

        if (savedInstanceState !=null)
            questionIndex = savedInstanceState.getInt("CurrentIndex",0);  // мы присвоили questionIndex значение индекса полученное из Сохраненногй Активности (см.внизу). При этом мы установили дефолтное значение "0" если Сохраненный Индекс еще пока пустой (начало работы с приложением)
        yesBtn = findViewById(R.id.yesBtn); // "ищем" переменную yesBtn (тип Button кстати) в нашем проекте (к чему она привязана по номеру id)
        noBtn = findViewById(R.id.noBtn);
        textView = findViewById(R.id.textView); // "нашли" и увязали переменную textView с элементом textView из activity_main.xml нашего проекта
        // обращаемся к textView, вызываем на нем метод setText и кладемм в него текст из нашего первого вопроса. Для этого -
        // обращаемся к массиву со всеми вопросами, у него обращаемся к элементу под номером questionIndex, и вызываем у него Геттер (getQuestionText)
        textView.setText(questions[questionIndex].getQuestionText());
        answerBtn = findViewById(R.id.answerBtn);

        yesBtn.setOnClickListener(new View.OnClickListener() {  // вызываем Метод OnClickListener у Класса View - ожидания нажатия кнопки и дальгейшего действия после нажатия..)
            @Override
            public void onClick(View view) {    // проверяем верно ли мы нажали кнопку "Да"
                choiсe = "Да";
                checkAnswer(true);
                /*if (questions[questionIndex].isAnswerTrue()) {
                    // вызываем уведомление на экран через Класс Toast
                    Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Неправильно!", Toast.LENGTH_SHORT).show();
                }
                questionIndex++;
                if (questionIndex == questions.length) questionIndex = 0;   // если индекс массива равен длине массива - переводим индекс в начальное знаечение = 0
                textView.setText(questions[questionIndex].getQuestionText());*/
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    // проверяем, верно ли мы нажали кнопку "Нет    "
                choiсe = "Нет";
                checkAnswer(false);
               /* if (!questions[questionIndex].isAnswerTrue()) {     // поставили знак '!' - для инверсии предыдущего метода if
                    // вызываем уведомление на экран через Класс Toast
                    Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Неправильно!", Toast.LENGTH_SHORT).show();
                }
                // questionIndex++;
                questionIndex = (questionIndex+1)%questions.length; // остаток от деления 0,1,2,3,4 - всегда равен 0,1,2,3 и 4. А вот остаток от деления 5 на 5 = 0. Мы вернулись в начало цикла (к "0"-му индекусу)
                textView.setText(questions[questionIndex].getQuestionText());*/
            }
        });

        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AnswerActivity.class); // создаем Намерение (Intent), намерение запустить новую Активность, указывая Кто запускает (MainActivity.this) и Что запускает (AnswerActivity.class). Class - потому, что мы запускаем уже скомпилированное приложение (это могут быть и другие, сторонние Приложения!)
                intent.putExtra("answer",questions[questionIndex].isAnswerTrue());  // таким образом мы "грузим" в "уходящий" intent Ответ - в нашем случае: "был ли ответ на наш вопрос правильным или нет"
                startActivity(intent);  // и запускаем нашу новую Активность (нашу intent)
            }
        });

    }
    public void checkAnswer (boolean btn){
        // реализовываем проверку - какую кнопку нажал пользователь
        if ((questions[questionIndex].isAnswerTrue() && btn) || (!questions[questionIndex].isAnswerTrue() && !btn)) {  // Если ((правильный ответ true + нажата кнопка Да (true) ИЛИ праввильный ответ false и нажата кнопка Нет (false)) - проверяем сразу два условия одновременно
            // вызываем уведомление на экран через Класс Toast
            Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
            // добавляем в resultArray запись "Зачет!"
            resultArray.add(questionIndex,getString(questions[questionIndex].getQuestionText())+" Ответ: "+choiсe+". Зачет");
            // увеличиваем число правильных ответов
            resume_int ++;
        } else {
            Toast.makeText(MainActivity.this, "Неправильно!", Toast.LENGTH_SHORT).show();
            // добавляем в resultArray запись "Незачет!"
            resultArray.add(questionIndex,getString(questions[questionIndex].getQuestionText())+" Ответ: "+choiсe+". Незачет");
        }
        questionIndex++;

        if (questionIndex == questions.length) {    // если индекс массива равен длине массива:
            questionIndex = 0;   // 1) переводим индекс в начальное знаечение = 0
            openResult();
            ArrayList<String> resultArray = new ArrayList();
        }else
            textView.setText(questions[questionIndex].getQuestionText());
   }



    public void openResult(){
        Intent intent = new Intent(MainActivity.this,AnswerActivityFull.class); // создаем Намерение (Intent), намерение запустить новую Активность, указывая Кто запускает (MainActivity.this) и Что запускает (AnswerActivity.class). Class - потому, что мы запускаем уже скомпилированное приложение (это могут быть и другие, сторонние Приложения!)
        intent.putStringArrayListExtra("answer", this.resultArray);
        intent.putExtra("answer_resume",resume_int);
        startActivity(intent);  // и запускаем нашу новую Активность (нашу intent)
    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {    // переназначаем метод Сохранения Активности
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("CurrentIndex",questionIndex);    // записываем в Сохранение Активности значение последнего индекса нашего вопроса на экране
    }




    @Override               // переопределяем Родительские методы для поиска точки, когда экран приложения перевернулся в горизонтальное положение
    protected void onStart() {
        super.onStart();    // так как метод onStart() вызывается из Родителя - мы используем вызов из родителя: super.onStart()
        Log.i("SYSTEM_INF: ","Вызван метод onStart()");
    }




    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SYSTEM_INF: ","Вызван метод onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("SYSTEM_INF: ","Вызван метод onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("SYSTEM_INF: ","Вызван метод onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("SYSTEM_INF: ","Вызван метод onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("SYSTEM_INF: ","Вызван метод onDestroy()");
    }

}