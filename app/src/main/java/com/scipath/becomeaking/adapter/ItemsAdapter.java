package com.scipath.becomeaking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.contract.callback.ItemCallback;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.model.enums.InteractionResult;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.item.Food;
import com.scipath.becomeaking.model.item.Item;
import com.scipath.becomeaking.model.item.SelectableItem;
import com.scipath.becomeaking.model.item.Work;
import com.scipath.becomeaking.view.view.CustomLinearLayout;
import com.scipath.becomeaking.view.fragment.DialogueFragment;

import java.util.List;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    // Variables
    private final List<IItem> items;
    private final ItemCallback callback;
    private final Context context;


    // Constructor
    public ItemsAdapter(List<IItem> items, ItemCallback callback, Context context) {
        this.items = items;
        this.callback = callback;
        this.context = context;
    }


    // ViewHolder subclass
    // Provide a reference to the type of views that you are using
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CustomLinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.item_layout);
        }

        public CustomLinearLayout getLayout() {
            return layout;
        }

        public TextView getNameView() {
            return layout.findViewById(R.id.text_view_item);
        }

        public TextView getRequirementView() {
            return layout.findViewById(R.id.text_view_requirement);
        }

        public ImageView getImageView() {
            return layout.findViewById(R.id.image_view_item);
        }

        public RecyclerView getStatsView() {
            return layout.findViewById(R.id.stats_list);
        }

        public Button getButtonInteractView() {
            return layout.findViewById(R.id.button_interact);
        }

        public void updateButtonInteractState(IItem item, Context context) {
            Button button = getButtonInteractView();
            button.setText(item.getInteractionName(context));
            int color;
            if (item.getState() == Item.State.NotBought ||
                    item.getState() == SelectableItem.State.NotBought ||
                    item.getState() == SelectableItem.State.Bought ||
                    item.getState() == Food.State.NotInRation ||
                    item.getState() == Work.State.NotCompleted){
                color = context.getColor(R.color.transparent_green);
                button.setEnabled(true);
            } else if (item.getState() == Item.State.Bought ||
                    item.getState() == Work.State.Completed) {
                color = context.getColor(R.color.transparent_red);
                button.setEnabled(false);
            } else {
                color = context.getColor(R.color.purple);
                if (item == item.getCategory().getItem(0) &&
                        item.getState() == SelectableItem.State.Selected) {
                    button.setEnabled(false);
                }
            }
            button.setBackgroundColor(color);
        }
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_item, viewGroup, false);

        return new ItemsAdapter.ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Personage personage = BecomeAKing.getInstance().getPersonage();
        IItem item = items.get(position);
        item.setOnStateChanged(() -> viewHolder.updateButtonInteractState(item, context));
        ICategory category = item.getCategory();

        // Setting values to views
        if (category.getBackgroundDrawableId() != 0) {
            viewHolder.getLayout().setBackgroundDrawable(category.getBackgroundDrawableId());
        }

        viewHolder.getNameView().setText(item.getNameId());

        TextView textViewRequirement = viewHolder.getRequirementView();
        int strengthRequired = item.getStats().get(Stat.StrengthRequired);
        int reputationRequired = item.getStats().get(Stat.ReputationRequired);
        if (strengthRequired != 0 || reputationRequired != 0) {
            String requirement = (strengthRequired != 0) ?
                    Stat.StrengthRequired.getDescription(strengthRequired, context) :
                    Stat.ReputationRequired.getDescription(reputationRequired, context);
            textViewRequirement.setVisibility(View.VISIBLE);
            textViewRequirement.setText(requirement);
        } else {
            textViewRequirement.setVisibility(View.GONE);
            textViewRequirement.setText("");
        }

        viewHolder.getImageView().setImageResource(item.getImageId());
        viewHolder.getImageView().setContentDescription(item.getName(context));

        viewHolder.getStatsView().setLayoutManager(new LinearLayoutManager(context));
        viewHolder.getStatsView().setAdapter(new StatsAdapter(item.getStats(), context));

        viewHolder.updateButtonInteractState(item, context);
        viewHolder.getButtonInteractView().setOnClickListener(view -> {
            InteractionResult result = item.interact(personage);
            if (result == InteractionResult.Successful) {
                callback.call(item);
            } else {
                DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                        .setHeader(R.string.notification)
                        .setMessage(result.getMessageId())
                        .setButton1(R.string.got_it, null)
                        .build();
                dialogueFragment.show(((AppCompatActivity)context).getSupportFragmentManager(), "dialogue");
            }
        });
    }


    // Return the size dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }
}
