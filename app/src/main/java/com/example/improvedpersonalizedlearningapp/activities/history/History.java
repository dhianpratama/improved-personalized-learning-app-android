package com.example.improvedpersonalizedlearningapp.activities.history;

import java.util.List;

public class History{
    private String questionText;
    private String correctAnswer;
    private String userAnswer;
    private List<String> incorrectAnswers;

    public History( String questionText, String correctAnswer, List<String> incorrectAnswers, String userAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }


    public String getQuestionText()
    {
        return questionText;
    }

    public String getCorrectAnswer()
    {
        return correctAnswer;
    }

    public String getUserAnswer()
    {
        return userAnswer;
    }

    public List<String> getIncorrectAnswers()
    {
        return incorrectAnswers;
    }
}


