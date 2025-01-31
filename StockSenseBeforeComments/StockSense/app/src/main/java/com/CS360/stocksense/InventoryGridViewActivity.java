package com.CS360.stocksense;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.CS360.stocksense.Database.AppDatabase;
import com.CS360.stocksense.Database.Items;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InventoryGridViewActivity extends MainActivity {

    private RecyclerView recyclerView;
    private RecyclerGridViewAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_grid_view);

        db = AppDatabase.getInstance(this);
        recyclerView = findViewById(R.id.inventory_recycler_view);

        findViewById(R.id.nav_button2).setOnClickListener(v -> onNavButton2Click());
        findViewById(R.id.nav_button3).setOnClickListener(v -> onNavButton3Click());

        loadData();
    }
    @Override
    protected void onPause(){
        super.onPause();
        new Thread(() -> {
            List<Items> currentItems = adapter.getItemsList();
            for (Items item : currentItems) {
                db.itemsDao().update(item);
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            sortData(itemsList);
            runOnUiThread(() -> {
                if (adapter == null) {
                    adapter = new RecyclerGridViewAdapter(itemsList, this);
                    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.updateData(itemsList);
                }
            });
        }).start();
    }

    private List<Items> sortData(List<Items> itemsList){
        Collections.sort(itemsList, Comparator.comparing(Items::getItemName));
        return itemsList;
    }
}
