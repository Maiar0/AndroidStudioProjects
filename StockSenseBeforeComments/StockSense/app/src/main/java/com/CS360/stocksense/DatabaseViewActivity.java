package com.CS360.stocksense;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.CS360.stocksense.Database.AppDatabase;
import com.CS360.stocksense.Database.Items;
import java.util.List;

public class DatabaseViewActivity extends MainActivity {

    private RecyclerView recyclerView;
    private RecyclerListViewAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_view);

        db = AppDatabase.getInstance(this);
        recyclerView = findViewById(R.id.database_recycler_view);

        findViewById(R.id.nav_button1).setOnClickListener(v -> onNavButton1Click());
        findViewById(R.id.nav_button3).setOnClickListener(v -> onNavButton3Click());

        loadData();
    }

    @Override
    protected void onNewItemCreated(){
        super.onNewItemCreated();
        loadData();
    }
    private void loadData() {
        new Thread(() -> {
            List<Items> itemsList = db.itemsDao().getAllItems();
            runOnUiThread(() -> {
                adapter = new RecyclerListViewAdapter(itemsList, item -> showDeleteConfirmationDialog(item));
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

    private void showDeleteConfirmationDialog(Items item) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Item")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes", (dialog, which) -> deleteItem(item))
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteItem(Items item) {
        new Thread(() -> {
            db.itemsDao().delete(item);
            runOnUiThread(() -> {
                adapter.removeItem(item);
                showToast("Item deleted successfully");
            });
        }).start();
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(DatabaseViewActivity.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
