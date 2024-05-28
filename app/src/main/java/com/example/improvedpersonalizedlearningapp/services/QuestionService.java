package com.example.improvedpersonalizedlearningapp.services;

import com.example.improvedpersonalizedlearningapp.activities.generatetask.QuestionApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionService {
    @GET("api.php?amount=3")
    Call<QuestionApiResponse> getQuestions();
}

