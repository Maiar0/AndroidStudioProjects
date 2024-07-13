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

public class RecyclerGridViewAdapter extends RecyclerView.Adapter<RecyclerGridViewAdapter.ViewHolder> {

    private List<Items> itemsList;
    private int itemid;
    private Context context;

    public RecyclerGridViewAdapter(List<Items> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_grid_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items item = itemsList.get(position);
        itemid = item.getId();
        holder.itemName.setText(item.getItemName());
        holder.itemQuantity.setText("Q: " + item.getQuantity());
        holder.itemLocation.setText(item.getLocation());

        holder.incrementButton.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
        });

        holder.decrementButton.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() - 1);
            notifyItemChanged(position);
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemDetailsActivity.class);
            intent.putExtra("item_id", item.getId());
            intent.putExtra("source_activity", "GridView");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public void updateData(List<Items> newItemsList) {
        this.itemsList = newItemsList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemQuantity, itemLocation;
        Button incrementButton, decrementButton;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            itemLocation = itemView.findViewById(R.id.item_location);
            incrementButton = itemView.findViewById(R.id.increment_button);
            decrementButton = itemView.findViewById(R.id.decrement_button);
        }
    }
    public List<Items> getItemsList(){
        return itemsList;
    }

}
