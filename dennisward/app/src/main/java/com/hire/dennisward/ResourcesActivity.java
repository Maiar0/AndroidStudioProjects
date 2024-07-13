package com.hire.dennisward;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        // Set up button click listeners
        setUpWebView(R.id.button_this_application, "https://github.com/Maiar0/AndroidStudioProjects/tree/main/dennisward");
        setUpGithubView(R.id.button_extraction_safe_zones, "https://github.com/Maiar0/Portfolio/tree/main/DayZ%20Mods/ExtractionSafeZones", "https://youtu.be/kh62tO2i2l4?si=ImIab9V4HuOBD4fv");
        setUpGithubView(R.id.button_key_cards, "https://github.com/Maiar0/Portfolio/tree/main/DayZ%20Mods/KeyCards", "https://youtu.be/ENcGT9GphOU?si=AYtp8davKuriAZRO");
        setUpGithubView(R.id.button_maiar_clothing_pack, "https://github.com/Maiar0/Portfolio/tree/main/DayZ%20Mods/Maiar_ClothingPack", "https://youtu.be/fj3S-C97j-0?si=yrwLKSSGyfnCC_-3");
        setUpGithubView(R.id.button_maiar_stimpacks_v2, "https://github.com/Maiar0/Portfolio/tree/main/DayZ%20Mods/Maiar_Stimpacks_V2", "https://youtu.be/VNrjDHZrwjg?si=8rD9qRt0y-ri9Qq2");
        setUpGithubView(R.id.button_maiar_teleportation, "https://github.com/Maiar0/Portfolio/tree/main/DayZ%20Mods/Maiar_Teleportation", "https://youtu.be/0hgq7zrksMw?si=vdXAd1Gsb_ba0X_M");
        setUpWebView(R.id.button_maiar_raid_alarm, "https://github.com/Maiar0/Portfolio/tree/main/DayZ%20Mods/Maiar_RaidAlarm");
        setUpWebView(R.id.button_maiars_kits, "https://github.com/Maiar0/Portfolio/tree/main/DayZ%20Mods/Maiars_Kits");
        setUpWebView(R.id.button_maiars_trader_days, "https://github.com/Maiar0/Portfolio/tree/main/DayZ%20Mods/Maiars_TraderDays");
        setUpWebView(R.id.button_portfolio, "https://github.com/Maiar0/Portfolio");
    }


    private void setUpGithubView(int buttonId, final String githubUrl, final String videoUrl) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResourcesActivity.this, GithubViewActivity.class);
                intent.putExtra("github_url", githubUrl);
                intent.putExtra("video_url", videoUrl);
                startActivity(intent);
            }
        });
    }

    private void setUpWebView(int buttonId, final String videoUrl) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResourcesActivity.this, WebViewActivity.class);
                intent.putExtra("url", videoUrl);
                startActivity(intent);
            }
        });
    }

}