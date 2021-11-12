package com.example.edu_1313_quiz;

public class Question {
    public int questionText;    // не надо удивляться что тип 'int', поскольку наши Идентификаторы вопросов (из файла strings.xml) на самом деле храняться в виде Числовых "ссылок"
    public boolean answerTrue;

    public Question(int questionText, boolean answerTrue) {
        this.questionText = questionText;
        this.answerTrue = answerTrue;

    }

    public int getQuestionText() {
        return questionText;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

}
