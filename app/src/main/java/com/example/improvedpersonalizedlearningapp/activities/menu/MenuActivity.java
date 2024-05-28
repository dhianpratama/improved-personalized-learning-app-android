package com.example.improvedpersonalizedlearningapp.activities.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.improvedpersonalizedlearningapp.activities.profile.ProfileActivity;
import com.example.improvedpersonalizedlearningapp.R;
import com.example.improvedpersonalizedlearningapp.activities.history.HistoryActivity;
import com.example.improvedpersonalizedlearningapp.activities.home.HomeActivity;
import com.example.improvedpersonalizedlearningapp.activities.upgrade.UpgradeActivity;

public class MenuActivity extends AppCompatActivity {
 AppCompatButton myProfileButton;
 AppCompatButton  historyButton;
 AppCompatButton homeButton;
 AppCompatButton   upgradeButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        myProfileButton = findViewById(R.id.myProfileButton);
        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        historyButton = findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        upgradeButton = findViewById(R.id.upgradeButton);
        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, UpgradeActivity.class);
                startActivity(intent);
            }
        });

    }
}