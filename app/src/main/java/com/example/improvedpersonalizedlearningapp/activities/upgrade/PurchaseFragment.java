package com.example.improvedpersonalizedlearningapp.activities.upgrade;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.improvedpersonalizedlearningapp.R;
import com.example.improvedpersonalizedlearningapp.activities.main.MainActivity;
import com.example.improvedpersonalizedlearningapp.activities.menu.MenuActivity;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;


public class PurchaseFragment extends Fragment {

    private CardInputWidget cardInputWidget;
    private Stripe stripe;
    AppCompatButton payButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PaymentConfiguration.init(requireContext(), "pk_test_51PCKi0KL7fioSjeEEyqZLIGuARqfzEinVJVjwMzsIU01uY6hNHIVLYMnl1RGQSQgfII4Emx7fNcPZyzJ3L0HRzfh008QvHsxNl");

        stripe = new Stripe(requireContext(), PaymentConfiguration.getInstance(requireContext()).getPublishableKey());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchase, container, false);
        cardInputWidget = view.findViewById(R.id.card_input_widget);
        payButton = view.findViewById(R.id.payButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchase();
            }
        });

        return view;
    }

    private void purchase() {
        Card card = cardInputWidget.getCard();
        if (card != null) {
            stripe.createToken(
                    card,
                    new ApiResultCallback<Token>() {
                        @Override
                        public void onSuccess(@NonNull Token token) {
                            Toast.makeText(requireContext(), "Payment successful", Toast.LENGTH_SHORT).show();
                            getActivity();
                            Intent intent = new Intent(getActivity(), MenuActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(@NonNull Exception e) {
                            Toast.makeText(requireContext(), "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }
    }
}

