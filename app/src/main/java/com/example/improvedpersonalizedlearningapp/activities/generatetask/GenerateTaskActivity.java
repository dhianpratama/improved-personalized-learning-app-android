package com.example.improvedpersonalizedlearningapp.activities.generatetask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvedpersonalizedlearningapp.utils.HistoryDBHelper;
import com.example.improvedpersonalizedlearningapp.R;
import com.example.improvedpersonalizedlearningapp.activities.result.ResultsActivity;
import com.example.improvedpersonalizedlearningapp.services.QuestionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenerateTaskActivity extends AppCompatActivity {
    AppCompatButton submitButton;
    TextView textViewQuestions;
    RecyclerView questionsRecyclerView;
    private QuestionsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private QuestionService questionService;
    private Retrofit retrofit;
   HistoryDBHelper dbHelper;

    private static List<String> selectedAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_task);
        selectedAnswers = new ArrayList<>();

        dbHelper = new HistoryDBHelper(this);

        questionsRecyclerView = findViewById(R.id.questionsRecyclerView);
        submitButton = findViewById(R.id.submitButton);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        questionsRecyclerView.setLayoutManager(layoutManager);

        fetchQuestions();


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    for (String selectedAnswer : selectedAnswers) {
                        int position = selectedAnswers.indexOf(selectedAnswer);
                        Question question = adapter.getQuestion(position);
                        if (question != null) {
                            String questionText = question.getQuestion();
                            String correctAnswer = question.getCorrectAnswer();
                            List<String> incorrectAnswers = question.getIncorrectAnswers();

                            Log.d("Data to be inserted", "Question: " + questionText);
                            Log.d("Data to be inserted", "Correct Answer: " + correctAnswer);
                            Log.d("Data to be inserted", "Incorrect Answers: " + incorrectAnswers);
                            Log.d("Data to be inserted", "User Answer: " + selectedAnswer);

                            long newRowId = dbHelper.insertHistoryRecord(questionText, correctAnswer, incorrectAnswers, selectedAnswer);
                            if (newRowId == -1) {
                                Toast.makeText(getApplicationContext(), "Error: Failed to insert data into database", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("HistoryData", "Inserted data for Question: " + questionText);
                                Log.d("HistoryData", "\tCorrect Answer: " + correctAnswer);
                                Log.d("HistoryData", "\tIncorrect Answers: " + incorrectAnswers);
                                Log.d("HistoryData", "\tUser Answer: " + selectedAnswer);
                            }
                        }
                    }

                Intent intent = new Intent(GenerateTaskActivity.this, ResultsActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void onAnswerClicked(View view) {
        // Cast the clicked view to a RadioButton
        RadioButton radioButton = (RadioButton) view;

        // Check if the RadioButton is checked
        if (radioButton.isChecked()) {
            // Get the text of the selected answer
            String selectedAnswer = radioButton.getText().toString();
            // Add the selected answer to the list of selected answers
            selectedAnswers.add(selectedAnswer);
        }
    }

    public void fetchQuestions() {
        // Create Retrofit instance
        retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create QuestionService instance
        questionService = retrofit.create(QuestionService.class);

        // Make API call to get questions
        Call<QuestionApiResponse> call = questionService.getQuestions();
        call.enqueue(new Callback<QuestionApiResponse>() {
            @Override
            public void onResponse(Call<QuestionApiResponse> call, Response<QuestionApiResponse> response) {
                if (response.isSuccessful()) {
                    QuestionApiResponse questionApiResponse = response.body();
                    if (questionApiResponse != null) {
                        List<Question> questions = questionApiResponse.getResults();
                        adapter = new QuestionsAdapter(questions, GenerateTaskActivity.this);
                        questionsRecyclerView.setAdapter(adapter);

                        // Log out each question along with its incorrect answers
                        for (Question question : questions) {
                            Log.d("Question fetched from the API", question.getQuestion());
                            List<String> incorrectAnswers = question.getIncorrectAnswers();
                            Log.d("Incorrect Answers fetched from the API", incorrectAnswers.toString());
                        }
                    } else {
                        textViewQuestions.setText("Response body is null.");
                    }
                } else {
                    textViewQuestions.setText("Failed to fetch questions. Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<QuestionApiResponse> call, Throwable t) {
                textViewQuestions.setText("Failed to fetch questions. Error: " + t.getMessage());
            }
        });
    }
}
