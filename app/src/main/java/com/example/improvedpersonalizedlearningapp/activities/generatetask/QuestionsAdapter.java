package com.example.improvedpersonalizedlearningapp.activities.generatetask;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvedpersonalizedlearningapp.R;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {
    private List<Question> questions;
    private GenerateTaskActivity activity;
    public QuestionsAdapter(List<Question> questions, GenerateTaskActivity activity)

    {
        this.questions = questions;
        this.activity = activity;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.textViewQuestionNumber.setText("Question " + (position + 1));
        holder.textViewQuestion.setText(question.getQuestion());
        holder.radioGroupAnswers.removeAllViews();

        Log.d("Correct Answer from adapter", question.getCorrectAnswer());
        Log.d("Incorrect Answers from adapter", "Incorrect Answers from adapter:");
        for (String incorrectAnswer : question.getIncorrectAnswers()) {
            Log.d("Incorrect Answers from adapter", incorrectAnswer);
        }

        for (String incorrectAnswer : question.getIncorrectAnswers()) {
            RadioButton radioButton = new RadioButton(holder.itemView.getContext());
            radioButton.setText(incorrectAnswer);
            radioButton.setTextSize(16);
            radioButton.setTextColor(Color.WHITE);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onAnswerClicked(v);
                }
            });
            radioButton.setTag(holder.getAdapterPosition());
            holder.radioGroupAnswers.addView(radioButton);
        }

        RadioButton correctAnswerRadioButton = new RadioButton(holder.itemView.getContext());
        correctAnswerRadioButton.setText(question.getCorrectAnswer());
        correctAnswerRadioButton.setTextSize(16);
        correctAnswerRadioButton.setTextColor(Color.WHITE);
        correctAnswerRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onAnswerClicked(v);
            }
        });
        correctAnswerRadioButton.setTag(holder.getAdapterPosition());
        holder.radioGroupAnswers.addView(correctAnswerRadioButton);

    }


    @Override
    public int getItemCount() {
        return (questions.size());
    }

    public Question getQuestion(int position) {
        if (position >= 0 && position < questions.size()) {
            return questions.get(position);
        } else {
            return null;
        }
    }


    static class QuestionsViewHolder extends RecyclerView.ViewHolder{
        RadioGroup radioGroupAnswers;
        CardView cardViewQuestion;
        TextView textViewQuestionNumber;
        TextView textViewQuestion;
        RadioButton radioButtonAnswer1;


        QuestionsViewHolder(@NonNull View itemView){
            super(itemView);
            radioGroupAnswers = itemView.findViewById(R.id.radioGroupAnswers);
            cardViewQuestion = itemView.findViewById(R.id.cardViewQuestion);
            textViewQuestionNumber = itemView.findViewById(R.id.textViewQuestionNumber);
            textViewQuestion = itemView.findViewById(R.id.textViewQuestion);
            radioButtonAnswer1 = itemView.findViewById(R.id.radioButtonAnswer1);

        }
    }
}