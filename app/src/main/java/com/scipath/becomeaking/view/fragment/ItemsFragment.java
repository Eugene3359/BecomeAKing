package com.scipath.becomeaking.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.adapter.ItemsAdapter;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.model.item.Item;
import com.scipath.becomeaking.model.item.SelectableItem;
import com.scipath.becomeaking.model.item.Work;
import com.scipath.becomeaking.view.activity.Clicker1Activity;
import com.scipath.becomeaking.view.activity.Clicker2Activity;
import com.scipath.becomeaking.view.activity.GameActivity;
import com.scipath.becomeaking.view.view.CustomLinearLayout;

import java.util.List;


public class ItemsFragment extends BaseFragment {

    // Variables
    private int categoryId;
    private boolean isWorkCategory;

    // Models
    List<IItem> items;

    // Views
    LinearLayoutManager layoutManager;
    TextView textViewExperience;


    public static ItemsFragment newInstance() {
        return new ItemsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Personage personage = BecomeAKing.getInstance().getPersonage();
        Bundle args = getArguments();
        categoryId = args.getInt("categoryId");
        items = BecomeAKing.getInstance().getCategoryById(categoryId).getItems();
        isWorkCategory = items.get(0) instanceof Work;

        // Views
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_items);
        CustomLinearLayout layoutExperience = view.findViewById(R.id.layout_experience);
        textViewExperience = view.findViewById(R.id.text_view_experience);
        ImageButton buttonPreviousItem = view.findViewById(R.id.button_previous_item);
        ImageButton buttonNextItem = view.findViewById(R.id.button_next_item);
        Button buttonBack = view.findViewById(R.id.button_back);

        // Adapter
        ItemsAdapter itemsAdapter = new ItemsAdapter(items, item -> {
            GameActivity activity = (GameActivity)requireActivity();
            if (item instanceof Work) {
                activity.switchMenuButton(activity.findViewById(R.id.button_personage), new PersonageFragment());
                startClickerMiniGamer(item);
            } else if (item instanceof Item || item instanceof SelectableItem){
                personage.recalculateStats();
                activity.updateViews();
            }
        }, view.getContext());

        // Displaying items
        layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemsAdapter);
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView rv, int newState) {
                super.onScrollStateChanged(rv, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int position = layoutManager.findFirstCompletelyVisibleItemPosition();
                    if (position == RecyclerView.NO_POSITION) {
                        position = layoutManager.findFirstVisibleItemPosition();
                    }
                    if (position != RecyclerView.NO_POSITION) {
                        if (isWorkCategory) setItemsExperience(position);
                        if (position == 0) {
                            buttonPreviousItem.setVisibility(View.INVISIBLE);
                        } else {
                            buttonPreviousItem.setVisibility(View.VISIBLE);
                        }
                        if (position == items.size()-1) {
                            buttonNextItem.setVisibility(View.INVISIBLE);
                        } else {
                            buttonNextItem.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        // Experience layout
        if (isWorkCategory) {
            layoutExperience.setVisibility(View.VISIBLE);
            setItemsExperience(0);
        }

        // Button previous item
        buttonPreviousItem.setVisibility(View.INVISIBLE);
        buttonPreviousItem.setOnClickListener(v -> {
            int firstVisible = layoutManager.findFirstVisibleItemPosition();
            recyclerView.smoothScrollToPosition(Math.max(0, firstVisible - 1));
        });

        // Button next item
        if (items.size() <= 1) buttonNextItem.setVisibility(View.INVISIBLE);
        buttonNextItem.setOnClickListener(v -> {
            int lastVisible = layoutManager.findLastVisibleItemPosition();
            recyclerView.smoothScrollToPosition(lastVisible + 1);
        });

        // Button back
        buttonBack.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }

    public void setItemsExperience(int position) {
        textViewExperience.setText(getString(
                R.string.c_d,
                '+',
                ((Work)items.get(position)).getExperience()));
    }

    public void startClickerMiniGamer(IItem item) {
        Intent intent;
        if (categoryId == 10) {
            intent = new Intent(getContext(), Clicker1Activity.class);
        } else if (categoryId == 11) {
            intent = new Intent(getContext(), Clicker2Activity.class);
        } else {
            return;
        }
        intent.putExtra("work", item);
        getContext().startActivity(intent);
    }
}