package com.scipath.becomeaking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.view.activity.ClickerMiniGameActivity;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.Item;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.view.customview.CustomLinearLayout;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    // Variables
    private int categoryId;
    private Category category;
    private Callback callback;
    private Context context;


    // Constructor
    public ItemsAdapter(int categoryId, Callback callback, Context context) {
        this.categoryId = categoryId;
        this.category = BecomeAKing.getInstance().getCurrentCategories().get(categoryId);
        this.callback = callback;
        this.context = context;
    }


    // ViewHolder subclass
    // Provide a reference to the type of views that you are using
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CustomLinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            layout = view.findViewById(R.id.item_layout);
        }

        public CustomLinearLayout getLayout() {
            return layout;
        }

        public TextView getItemNameView() {
            return layout.findViewById(R.id.text_view_item);
        }

        public ImageView getItemImageView() {
            return layout.findViewById(R.id.image_view_item);
        }

        public RecyclerView getItemStatsView() {
            return layout.findViewById(R.id.stats_list);
        }

        public Button getItemButtonBuyView() {
            return layout.findViewById(R.id.buy);
        }

        public void resetItemButtonBuyState(Item item, int categoryId, Context context) {
            Button button = getItemButtonBuyView();
            button.setEnabled(true);
            button.setBackgroundColor(context.getColor(R.color.transparent_green));

            if (categoryId == 0) {
                button.setText(context.getString(R.string.add_to_ration));
            } else if (categoryId >= 10) {
                button.setText(context.getString(R.string.start));
            } else {
                button.setText(context.getString(R.string.buy_d, item.getCost()));
            }
        }

        public void setItemButtonBuyNotEnabled(int categoryId, Context context) {
            Button button = getItemButtonBuyView();
            if (categoryId == 0) {
                button.setText(context.getString(R.string.in_ration));
            } else if (categoryId >= 10) {
                button.setText(context.getString(R.string.started));
            }
            else {
                button.setText(context.getString(R.string.bought));
            }
            button.setBackgroundColor(context.getColor(R.color.transparent_red));
            button.setEnabled(false);
        }
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_item, viewGroup, false);

        return new ItemsAdapter.ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Personage personage = BecomeAKing.getInstance().getCurrentPersonage();
        Item item = category.getItems().get(position);

        // Setting values to views
        viewHolder.getItemNameView().setText(item.getNameId());
        viewHolder.getItemImageView().setImageResource(item.getImageId());
        viewHolder.getItemImageView().setContentDescription(item.getName(context));
        viewHolder.getItemStatsView().setLayoutManager(new LinearLayoutManager(context));
        viewHolder.getItemStatsView().setAdapter(new StatsAdapter(item.getStatBonuses(), context));
        if (!item.isBought()) {
            viewHolder.resetItemButtonBuyState(item, categoryId, context);

            viewHolder.getItemButtonBuyView().setOnClickListener(view -> {
                if (categoryId < 10) {
                    if (personage.getMoney() >= item.getCost()) {
                        personage.affectMoney(-item.getCost());
                        item.setBought(true);
                        viewHolder.setItemButtonBuyNotEnabled(categoryId, context);
                        callback.call();
                    } else {
                        Toast.makeText(context, context.getText(R.string.not_enough_money), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent intent = new Intent(context, ClickerMiniGameActivity.class);
                    intent.putExtra("item", item);
                    context.startActivity(intent);
                }
            });
        } else {
            viewHolder.setItemButtonBuyNotEnabled(categoryId, context);
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return category.getItems().size();
    }
}
