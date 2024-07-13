package com.cs360.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupNavigationBar() {
        Button navInventory = findViewById(R.id.nav_inventory);
        Button navReports = findViewById(R.id.nav_reports);
        Button navCreate = findViewById(R.id.nav_create);

        navInventory.setOnClickListener(v -> {
            Intent intent = new Intent(this, InventoryGridViewActivity.class);
            startActivity(intent);
        });

        navReports.setOnClickListener(v -> {
            // TODO: Add intent for Reports activity
        });

        navCreate.setOnClickListener(v -> {
            // TODO: Add intent for Create activity
        });
    }
}
