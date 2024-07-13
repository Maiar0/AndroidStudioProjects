package com.CS360.stocksense;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.CS360.stocksense.Database.AppDatabase;
import com.CS360.stocksense.Database.Items;
import com.google.android.material.textfield.TextInputEditText;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    protected AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);

        findViewById(R.id.nav_button1).setOnClickListener(v -> onNavButton1Click());
        findViewById(R.id.nav_button2).setOnClickListener(v -> onNavButton2Click());
        findViewById(R.id.nav_button3).setOnClickListener(v -> onNavButton3Click());
        setupLowInventoryWorker();
    }


    private void setupLowInventoryWorker() {
        WorkRequest workRequest = new PeriodicWorkRequest.Builder(LowInventoryWorker.class, 1, TimeUnit.HOURS)
                .build();
        WorkManager.getInstance(this).enqueue(workRequest);
    }

    protected void onNavButton1Click() {
        Intent intent = new Intent(this, InventoryGridViewActivity.class);
        startActivity(intent);
        Log.d("MainActivity", "Navigated to InventoryGridViewActivity");
    }

    protected void onNavButton2Click() {
        Intent intent = new Intent(this, DatabaseViewActivity.class);
        startActivity(intent);
        Log.d("MainActivity", "Navigated to DatabaseViewActivity");
    }

    protected void onNavButton3Click() {
        showCreateItemDialog();
        Log.d("MainActivity", "Navigation Button 3 Clicked");
    }

    private void showCreateItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Item");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_create_item, null);
        builder.setView(view);

        TextInputEditText itemIdInput = view.findViewById(R.id.item_id_input);
        TextInputEditText itemNameInput = view.findViewById(R.id.item_name_input);
        TextInputEditText itemQuantityInput = view.findViewById(R.id.item_quantity_input);
        TextInputEditText itemLocationInput = view.findViewById(R.id.item_location_input);
        TextInputEditText alertLevelInput = view.findViewById(R.id.alert_level_input);

        builder.setPositiveButton("Create", (dialog, which) -> {
            int itemId = Integer.parseInt(itemIdInput.getText().toString());
            String itemName = itemNameInput.getText().toString();
            int itemQuantity = Integer.parseInt(itemQuantityInput.getText().toString());
            String itemLocation = itemLocationInput.getText().toString();
            int alertLevel = Integer.parseInt(alertLevelInput.getText().toString());

            createNewItem(itemId, itemName, itemQuantity, itemLocation, alertLevel);
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void createNewItem(int id, String name, int quantity, String location, int alertLevel) {
        new Thread(() -> {
            Items newItem = new Items(id, name, quantity, location, alertLevel);
            db.itemsDao().insert(newItem);
            runOnUiThread(() -> {
                onNewItemCreated();
                showToast("Item created successfully");
            });
        }).start();
    }

    protected void onNewItemCreated() {
        // This method can be overridden in child activities if needed
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
