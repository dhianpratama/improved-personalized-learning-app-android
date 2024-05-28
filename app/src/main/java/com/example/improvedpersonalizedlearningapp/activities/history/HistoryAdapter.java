package com.example.improvedpersonalizedlearningapp.activities.history;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvedpersonalizedlearningapp.R;

import java.util.List;
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<History> historyItems;

    public HistoryAdapter(List<History> historyItems)

    {
        this.historyItems = historyItems;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        History historyItem = historyItems.get(position);

        holder.textViewQuestionNumber.setText("");
        holder.textViewQuestion.setText("");
        holder.textViewCorrectAnswer.setText("");
        holder.textViewUserAnswer.setText("");
        holder.incorrectAnswersLayout.removeAllViews();

        String questionText = historyItem.getQuestionText();
        String correctAnswer = historyItem.getCorrectAnswer();
        String userAnswer = historyItem.getUserAnswer();
        List<String> incorrectAnswers = historyItem.getIncorrectAnswers();

        int questionNumber = position + 1;
        holder.textViewQuestionNumber.setText("Question " + questionNumber);

        holder.textViewQuestion.setText(questionText);

        if (userAnswer.equals(correctAnswer)) {
            holder.textViewCorrectAnswer.setText(correctAnswer + "  -   Your answer is correct");
            holder.textViewCorrectAnswer.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green));
            holder.textViewUserAnswer.setVisibility(View.GONE);
        } else {
            holder.textViewCorrectAnswer.setText(correctAnswer + "  -  Correct Answer");
            holder.textViewCorrectAnswer.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green));
            holder.textViewUserAnswer.setText(userAnswer + "  -  Your Answer");
            holder.textViewUserAnswer.setTextColor(Color.RED);
            holder.textViewUserAnswer.setVisibility(View.VISIBLE);

            if (incorrectAnswers.size() > 1) {
                holder.incorrectAnswersLayout.setVisibility(View.VISIBLE);
                for (String answer : incorrectAnswers) {
                    TextView textViewIncorrectAnswer = new TextView(holder.itemView.getContext());
                    textViewIncorrectAnswer.setText(answer);
                    textViewIncorrectAnswer.setTextColor(Color.WHITE);
                    holder.incorrectAnswersLayout.addView(textViewIncorrectAnswer);
                }
            } else {
                holder.incorrectAnswersLayout.setVisibility(View.GONE);
            }
        }

        holder.incorrectAnswersLayout.removeAllViews(); // Clear existing views
        for (String answer : incorrectAnswers) {
            TextView textViewIncorrectAnswer = new TextView(holder.itemView.getContext());
            textViewIncorrectAnswer.setText(answer);
            textViewIncorrectAnswer.setTextColor(Color.WHITE);
            holder.incorrectAnswersLayout.addView(textViewIncorrectAnswer);
        }
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder{
        LinearLayout incorrectAnswersLayout;
        TextView textViewUserAnswer;
        CardView cardViewQuestion;
        TextView textViewQuestionNumber;
        TextView textViewQuestion;
        TextView textViewCorrectAnswer;


        HistoryViewHolder(@NonNull View itemView){
            super(itemView);
            incorrectAnswersLayout = itemView.findViewById(R.id.incorrectAnswersLayout);
            textViewUserAnswer = itemView.findViewById(R.id.textViewUserAnswer);
            cardViewQuestion = itemView.findViewById(R.id.cardViewQuestion);
            textViewQuestionNumber = itemView.findViewById(R.id.textViewQuestionNumber);
            textViewQuestion = itemView.findViewById(R.id.textViewQuestion);
            textViewCorrectAnswer = itemView.findViewById(R.id.textViewCorrectAnswer);

        }
    }
}
