package com.cs360.projectone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    private final List<Items> inventoryList;
    private final Context context;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Items item);
    }

    public InventoryAdapter(List<Items> inventoryList, Context context, OnItemClickListener onItemClickListener) {
        this.inventoryList = inventoryList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_inventory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items item = inventoryList.get(position);
        holder.bind(item, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemQuantity;
        TextView itemLocation;
        Button incrementButton;
        Button decrementButton;
        String itemId;

        ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            itemLocation = itemView.findViewById(R.id.item_location);
            incrementButton = itemView.findViewById(R.id.increment_button);
            decrementButton = itemView.findViewById(R.id.decrement_button);
        }

        void bind(Items item, OnItemClickListener onItemClickListener) {
            itemId = item.getId();
            itemName.setText(item.getItemName());
            itemQuantity.setText("Q:" + item.getQuantity());
            itemLocation.setText(item.getLocation());

            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(item));

            incrementButton.setOnClickListener(v -> {
                item.setQuantity(item.getQuantity() + 1);
                itemQuantity.setText("Q:" + item.getQuantity());
            });

            decrementButton.setOnClickListener(v -> {
                item.setQuantity(item.getQuantity() - 1);
                itemQuantity.setText("Q:" + item.getQuantity());
            });
        }
    }
}
