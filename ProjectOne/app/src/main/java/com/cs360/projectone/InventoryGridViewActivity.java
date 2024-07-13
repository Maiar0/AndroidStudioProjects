package com.cs360.projectone;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.util.Log;

public class InventoryGridViewActivity extends AppCompatActivity {
    private static final String TAG = "InventoryGridView";
    private RecyclerView recyclerView;
    private InventoryAdapter inventoryAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_grid_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.inventory_recycler_view);

        List<Items> inventoryList = InventoryDatabaseHelper.getAllItems(databaseHelper.getReadableDatabase());

        if (inventoryList != null && !inventoryList.isEmpty()) {
            inventoryAdapter = new InventoryAdapter(inventoryList, this, item -> {
                Intent intent = new Intent(InventoryGridViewActivity.this, ItemDetailActivity.class);
                intent.putExtra(ItemDetailActivity.EXTRA_ITEM_ID, item.getId());
                Log.d("InventoryGridView", "Launching ItemDetailActivity with itemId: " + item.getId());
                startActivity(intent);
            });

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(inventoryAdapter);
        } else {
            Log.e(TAG, "No inventory items found.");
        }
    }
}
