package com.scipath.becomeaking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.InteractionResult;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.item.Work;
import com.scipath.becomeaking.view.view.CustomLinearLayout;
import com.scipath.becomeaking.view.fragment.DialogueFragment;
import com.scipath.becomeaking.view.fragment.ItemsFragment;

import java.util.ArrayList;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    // Variables
    private final ArrayList<ICategory> categories;
    private final Context context;


    // Constructor
    public CategoriesAdapter(ArrayList<ICategory> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }


    // ViewHolder subclass
    // Provide a reference to the type of views that you are using
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.category_layout);
        }

        public LinearLayout getLayout() {
            return layout;
        }

        public TextView getNameView() {
            return layout.findViewById(R.id.text_view_name);
        }

        public TextView getRequirementView() {
            return layout.findViewById(R.id.text_view_requirement);
        }

        public ImageView getImageView() {
            return layout.findViewById(R.id.image_view_category);
        }

        public CustomLinearLayout getStatsLayout() {
            return layout.findViewById(R.id.layout_stats);
        }

        public RecyclerView getStatsList() {
            return layout.findViewById(R.id.stats_list);
        }

        public Button getButtonSelect() {
            return layout.findViewById(R.id.button_select);
        }

        public void updateButtonSelectState(Work work, Context context) {
            Button button = getButtonSelect();
            if (work.getState() == Work.State.NotSelected) {
                button.setText(R.string.select);
                button.setBackgroundColor(context.getColor(R.color.transparent_green));
            } else if (work.getState() == Work.State.Selected) {
                button.setText(R.string.remove);
                button.setBackgroundColor(context.getColor(R.color.transparent_red));
            }
        }
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_category, viewGroup, false);

        return new CategoriesAdapter.ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        ICategory category = categories.get(position);
        if (category.getId() == 4) {
            category.getItem(0).setImageId(BecomeAKing.getInstance()
                    .getCategories().get(1).getSelectedItem().getImageId());
        }

        // Setting values to views
        viewHolder.getNameView().setText(category.getNameId());

        if (category.getImageId()!= 0)
            viewHolder.getImageView().setImageResource(category.getImageId());
        viewHolder.getImageView().setContentDescription(category.getName(context));

        if (category.getBackgroundDrawableId() != 0)
            viewHolder.getStatsLayout().setBackgroundDrawable(category.getBackgroundDrawableId());

        IStats stats;
        if (category.getItems().size() == 1 && category.getItem(0) instanceof Work) {
            Work work = (Work) category.getItem(0);
            stats = work.getStats();
            setOnButtonSelectClick(work, viewHolder);
        } else {
            stats = category.getStats();
            changeFragmentOnCategoryClick(category, viewHolder.getLayout());
        }

        setRequirementView(stats, viewHolder.getRequirementView());

        viewHolder.getStatsList().setLayoutManager(new LinearLayoutManager(context));
        StatsAdapter statsAdapter = new StatsAdapter(stats, context);
        statsAdapter.setTextColor(R.color.text_grayish_brown);
        viewHolder.getStatsList().setAdapter(statsAdapter);
    }


    private void setRequirementView(IStats stats, TextView requirementView) {
        int reputationRequired = stats.get(Stat.ReputationRequired);
        int horseAndWeaponRequired = stats.get(Stat.HorseAndWeaponRequired);
        String requirement = "";

        if (reputationRequired != 0) {
            requirement = Stat.ReputationRequired.getDescription(reputationRequired, context);
        } else if (horseAndWeaponRequired != 0) {
            requirement = Stat.HorseAndWeaponRequired.getName(context);
        }

        if (!requirement.isEmpty()) {
            requirementView.setVisibility(View.VISIBLE);
            requirementView.setText(requirement);
        }
    }
    
    private void setOnButtonSelectClick(Work work, ViewHolder viewHolder) {
        Button buttonSelect = viewHolder.getButtonSelect();
        buttonSelect.setVisibility(View.VISIBLE);
        viewHolder.updateButtonSelectState(work, context);
        work.setOnStateChanged(() -> viewHolder.updateButtonSelectState(work, context));

        viewHolder.getButtonSelect().setOnClickListener(view -> {
            InteractionResult interactionResult = BecomeAKing.getInstance().getPersonage().interact(work);
            if (interactionResult != InteractionResult.Successful) {
                DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                        .setHeader(R.string.notification)
                        .setMessage(interactionResult.messageId)
                        .setButton1(R.string.got_it, null)
                        .build();
                dialogueFragment.show(((AppCompatActivity)context).getSupportFragmentManager(), "dialogue");
            }
        });
    }

    private void changeFragmentOnCategoryClick(ICategory category, LinearLayout layout) {
        layout.setOnClickListener(view -> {
            if (!category.getItems().isEmpty()) {
                Fragment fragment = new ItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", category.getId());
                fragment.setArguments(bundle);
                ((FragmentActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


    // Return the size dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categories.size();
    }
}
