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
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.view.customview.CustomLinearLayout;
import com.scipath.becomeaking.view.fragment.DialogueFragment;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    // Variables
    private final ICategory category;
    private final ItemCallback callback;
    private final Context context;


    // Constructor
    public ItemsAdapter(int categoryId, ItemCallback callback, Context context) {
        this.category = BecomeAKing.getInstance().getCategoryById(categoryId);
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

        public TextView getItemNameView() {
            return layout.findViewById(R.id.text_view_item);
        }

        public TextView getItemRequirementView() {
            return layout.findViewById(R.id.text_view_requirement);
        }

        public ImageView getItemImageView() {
            return layout.findViewById(R.id.image_view_item);
        }

        public RecyclerView getItemStatsView() {
            return layout.findViewById(R.id.stats_list);
        }

        public Button getItemButtonInteractView() {
            return layout.findViewById(R.id.button_interact);
        }

        public void resetItemButtonInteractState(IItem item, Context context) {
            Button button = getItemButtonInteractView();
            button.setText(item.getInteractionName(context));
            button.setBackgroundColor(context.getColor(R.color.transparent_green));
            button.setEnabled(true);
        }

        public void setItemButtonInteractNotEnabled(IItem item, Context context) {
            Button button = getItemButtonInteractView();
            button.setText(item.getInteractionResultNameId());
            button.setBackgroundColor(context.getColor(R.color.transparent_red));
            button.setEnabled(false);
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
        IItem item = category.getItems().get(position);

        // Setting values to views
        viewHolder.getItemNameView().setText(item.getNameId());

        TextView textViewRequirement = viewHolder.getItemRequirementView();
        int strengthRequired = item.getStats().get(Stat.StrengthRequired);
        int reputationRequired = item.getStats().get(Stat.ReputationRequired);
        if(strengthRequired != 0 || reputationRequired != 0) {
            String requirement = (strengthRequired == 0) ?
                    Stat.ReputationRequired.getDescription(reputationRequired, context) :
                    Stat.StrengthRequired.getDescription(strengthRequired, context);
            textViewRequirement.setVisibility(View.VISIBLE);
            textViewRequirement.setText(requirement);
        }

        viewHolder.getItemImageView().setImageResource(item.getImageId());
        viewHolder.getItemImageView().setContentDescription(item.getName(context));

        viewHolder.getItemStatsView().setLayoutManager(new LinearLayoutManager(context));
        viewHolder.getItemStatsView().setAdapter(new StatsAdapter(item.getStats(), context));

        if (!item.isInteracted()) {
            viewHolder.resetItemButtonInteractState(item, context);

            viewHolder.getItemButtonInteractView().setOnClickListener(view -> {
                int code = item.interact(personage);
                int messageId = 0;

                switch (code) {
                    case 0:
                        callback.call(item);
                        viewHolder.setItemButtonInteractNotEnabled(item, context);
                        break;
                    case -1:
                        messageId = R.string.not_enough_money;
                        break;
                    case -2:
                        messageId = R.string.not_enough_strength_skill_points;
                        break;
                    case -3:
                        messageId = R.string.not_enough_reputation;
                        break;
                    case -4:
                        messageId = R.string.you_dont_have_a_time;
                        break;
                    case -10:
                        messageId = R.string.null_personage_error;
                }
                if (messageId != 0) {
                    DialogueFragment.newInstance(messageId, R.string.ok)
                            .show(((AppCompatActivity)context).getSupportFragmentManager(), "dialogue");
                }
            });
        } else {
            viewHolder.setItemButtonInteractNotEnabled(item, context);
        }
    }


    // Return the size dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return category.getItems().size();
    }
}
