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
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.item.Work;
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

        public TextView getCategoryNameView() {
            return layout.findViewById(R.id.text_view_name);
        }

        public TextView getCategoryRequirementView() {
            return layout.findViewById(R.id.text_view_requirement);
        }

        public ImageView getCategoryImageView() {
            return layout.findViewById(R.id.image_view_category);
        }

        public RecyclerView getCategoryStatsView() {
            return layout.findViewById(R.id.stats_list);
        }

        public Button getCategoryButtonSelect() {
            return layout.findViewById(R.id.button_select);
        }

        public void setCategoryButtonSelectStateNotSelected(Context context) {
            Button button = getCategoryButtonSelect();
            button.setText(R.string.select);
            button.setBackgroundColor(context.getColor(R.color.transparent_green));
        }

        public void setCategoryButtonSelectStateSelected(Context context) {
            Button button = getCategoryButtonSelect();
            button.setText(R.string.remove);
            button.setBackgroundColor(context.getColor(R.color.transparent_red));
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

        // Setting values to views
        // Category name and image
        viewHolder.getCategoryNameView().setText(category.getNameId());
        if (category.getImageId()!= 0)
            viewHolder.getCategoryImageView().setImageResource(category.getImageId());
        viewHolder.getCategoryImageView().setContentDescription(category.getName(context));

        IStats stats;
        if (category.getItems().size() == 1 && category.getItems().get(0) instanceof Work) {
            Work work = (Work) category.getItems().get(0);
            stats = work.getStats();
            interactOnButtonSelectClick(work, viewHolder);
        } else {
            stats = category.getStats();
            changeFragmentOnCategoryClick(position, viewHolder.getLayout());
        }

        setRequirementView(stats, viewHolder.getCategoryRequirementView());

        viewHolder.getCategoryStatsView().setLayoutManager(new LinearLayoutManager(context));
        viewHolder.getCategoryStatsView().setAdapter(new StatsAdapter(stats, context));
    }


    private void setRequirementView(IStats stats, TextView requirementView) {
        int strengthRequired = stats.get(Stat.StrengthRequired);
        int reputationRequired = stats.get(Stat.ReputationRequired);
        int horseAndWeaponRequired = stats.get(Stat.HorseAndWeaponRequired);
        String requirement = "";

        if (strengthRequired != 0) {
            requirement = Stat.StrengthRequired.getDescription(strengthRequired, context);
        }
        if (reputationRequired != 0) {
            requirement = Stat.ReputationRequired.getDescription(reputationRequired, context);
        }
        if (horseAndWeaponRequired != 0) {
            requirement = Stat.HorseAndWeaponRequired.getName(context);
        }

        if (!requirement.isEmpty()) {
            requirementView.setVisibility(View.VISIBLE);
            requirementView.setText(requirement);
        }
    }
    
    private void interactOnButtonSelectClick(Work work, ViewHolder viewHolder) {
        Button buttonSelect = viewHolder.getCategoryButtonSelect();
        buttonSelect.setVisibility(View.VISIBLE);
        if (!work.isInteracted()) {
            viewHolder.setCategoryButtonSelectStateNotSelected(context);
        } else {
            viewHolder.setCategoryButtonSelectStateSelected(context);
        }
        viewHolder.getCategoryButtonSelect().setOnClickListener(view -> {
            if (!work.isInteracted()) {
                int code = work.interact(BecomeAKing.getInstance().getPersonage());
                int messageId = 0;

                switch (code) {
                    case 0:
                        viewHolder.setCategoryButtonSelectStateSelected(context);
                        break;
                    case -3:
                        messageId = R.string.not_enough_reputation;
                        break;
                    case -4:
                        messageId = R.string.you_dont_have_a_time;
                        break;
                    case -5:
                        messageId = R.string.horse_and_weapon_required;
                        break;
                    case -10:
                        messageId = R.string.null_personage_error;
                }
                if (messageId != 0) {
                    DialogueFragment.newInstance(messageId, R.string.got_it)
                            .show(((AppCompatActivity)context).getSupportFragmentManager(), "dialogue");
                }
            } else {
                work.setInteracted(false);
                Work.refreshInteractionCounter();
                viewHolder.setCategoryButtonSelectStateNotSelected(context);
            }
        });
    }

    private void changeFragmentOnCategoryClick(int position, LinearLayout layout) {
        layout.setOnClickListener(view -> {
            if (!categories.get(position).getItems().isEmpty()) {
                Fragment fragment = new ItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", categories.get(position).getId());
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
