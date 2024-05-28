package com.example.improvedpersonalizedlearningapp.activities.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvedpersonalizedlearningapp.R;
import com.example.improvedpersonalizedlearningapp.activities.history.History;
import com.example.improvedpersonalizedlearningapp.activities.history.HistoryActivity;
import com.example.improvedpersonalizedlearningapp.utils.HistoryDBHelper;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    RecyclerView resultsRecyclerView;
    ResultsAdapter adapter;
    HistoryDBHelper dbHelper;
    AppCompatButton continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        dbHelper = new HistoryDBHelper(this);

        List<History> historyData = dbHelper.getAllData();

        if (historyData != null && !historyData.isEmpty()) {
            List<Result> results = new ArrayList<>();
            for (History item : historyData) {
                String selectedAnswer = item.getUserAnswer();
                String correctAnswer = item.getCorrectAnswer();
                boolean isCorrect = selectedAnswer.equals(correctAnswer);

                results.add(new Result(selectedAnswer, correctAnswer, isCorrect));
            }

            resultsRecyclerView = findViewById(R.id.resultsRecyclerView);
            adapter = new ResultsAdapter(results);
            resultsRecyclerView.setAdapter(adapter);
            resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }


}


