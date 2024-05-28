package com.example.improvedpersonalizedlearningapp.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.improvedpersonalizedlearningapp.R;

import java.util.Random;

public class ProfileActivity extends AppCompatActivity {
    CardView cardViewShareButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        cardViewShareButton = findViewById(R.id.cardViewShareButton);
        cardViewShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareProfileLink();
            }
        });
    }

    private void shareProfileLink() {
        String profileId = generateProfileId();

        String profileUrl = "https://learningapp.com/myprofile/" + profileId;

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out my profile!");
        shareIntent.putExtra(Intent.EXTRA_TEXT, profileUrl);

        startActivity(Intent.createChooser(shareIntent, "Share Profile Link"));
    }

    private String generateProfileId() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }
}
