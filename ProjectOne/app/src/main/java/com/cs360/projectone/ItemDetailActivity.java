package com.cs360.projectone;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.database.sqlite.SQLiteDatabase;


public class ItemDetailActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM_ID = "com.cs360.projectone.ITEM_ID";
    private String itemId;
    private DatabaseHelper databaseHelper;
    private TextView itemDetailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        itemId = getIntent().getStringExtra(EXTRA_ITEM_ID);
        if (itemId == null) {
            Log.e("ItemDetailActivity", "No itemId received in the intent.");
            finish();
            return;
        }

        Log.d("ItemDetailActivity", "Received itemId: " + itemId);

        databaseHelper = new DatabaseHelper(this);

        itemDetailText = findViewById(R.id.item_detail_text);

        loadItemDetails();
    }

    private void loadItemDetails() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Items item = InventoryDatabaseHelper.getItemById(db, itemId);
        if (item != null) {
            itemDetailText.setText("Details for item ID: " + itemId + "\n\n" +
                    "Name: " + item.getItemName() + "\n" +
                    "Quantity: " + item.getQuantity() + "\n" +
                    "Location: " + item.getLocation());
        } else {
            Log.e("ItemDetailActivity", "Item not found for id: " + itemId);
            finish();
        }
    }
}
