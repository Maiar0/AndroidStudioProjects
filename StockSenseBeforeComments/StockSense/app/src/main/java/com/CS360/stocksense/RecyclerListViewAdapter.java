package com.CS360.stocksense;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.CS360.stocksense.Database.Items;
import java.util.List;

public class RecyclerListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private List<Items> itemsList;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Items item);
    }

    public RecyclerListViewAdapter(List<Items> itemsList, OnDeleteClickListener onDeleteClickListener) {
        this.itemsList = itemsList;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_database_view_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_database_view, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_HEADER) {
        } else {
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            Items item = itemsList.get(position - 1); // Adjust for header
            itemHolder.itemId.setText(String.valueOf(item.getId()));
            itemHolder.itemName.setText(item.getItemName());
            itemHolder.itemQuantity.setText(String.valueOf(item.getQuantity()));
            itemHolder.itemLocation.setText(item.getLocation());

            itemHolder.deleteButton.setOnClickListener(v -> onDeleteClickListener.onDeleteClick(item));

            itemHolder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), ItemDetailsActivity.class);
                intent.putExtra("item_id", item.getId());
                intent.putExtra("source_activity", "ListView");
                holder.itemView.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size() + 1; // Adjust for header
    }

    public void removeItem(Items item) {
        int position = itemsList.indexOf(item);
        if (position != -1) {
            itemsList.remove(position);
            notifyItemRemoved(position + 1); // Adjust for header
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemId, itemName, itemQuantity, itemLocation;
        Button deleteButton;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemId = itemView.findViewById(R.id.item_id);
            itemName = itemView.findViewById(R.id.item_name);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            itemLocation = itemView.findViewById(R.id.item_location);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
