package com.example.improvedpersonalizedlearningapp.activities.upgrade;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.improvedpersonalizedlearningapp.R;

public class UpgradeActivity extends AppCompatActivity{
        AppCompatButton cardViewPurchaseButton;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_upgrade);

            cardViewPurchaseButton = findViewById(R.id.cardViewPurchaseButton);
            cardViewPurchaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PurchaseFragment fragment = new PurchaseFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }
}




