package com.scipath.becomeaking.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.adapter.ItemsAdapter;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.model.item.Item;
import com.scipath.becomeaking.model.item.Work;
import com.scipath.becomeaking.view.activity.Clicker1Activity;
import com.scipath.becomeaking.view.activity.Clicker2Activity;
import com.scipath.becomeaking.view.activity.GameActivity;
import com.scipath.becomeaking.view.customview.CustomLinearLayout;

import java.util.List;


public class ItemsFragment extends BaseFragment {

    // Variables
    private int categoryId;

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

        // Getting personage and categoryId
        Bundle args = getArguments();
        categoryId = args.getInt("categoryId");
        Personage personage = BecomeAKing.getInstance().getPersonage();
        items = BecomeAKing.getInstance().getCategoryById(categoryId).getItems();

        // Views
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_items);
        CustomLinearLayout layoutExperience = view.findViewById(R.id.layout_experience);
        textViewExperience = view.findViewById(R.id.text_view_experience);
        ImageButton buttonPreviousItem = view.findViewById(R.id.button_previous_item);
        ImageButton buttonNextItem = view.findViewById(R.id.button_next_item);
        Button buttonBack = view.findViewById(R.id.button_back);

        // Adapter
        ItemsAdapter itemsAdapter = new ItemsAdapter(categoryId, item -> {
            GameActivity activity = (GameActivity)requireActivity();
            if (item instanceof Work) {
                activity.switchMenuButton(activity.findViewById(R.id.button_personage), new PersonageFragment());
                startClickerMiniGamer(item);
            } else if (item instanceof Item){
                personage.recalculateStats();
                activity.updateViews();
            }
        }, view.getContext());

        // Displaying items
        layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setOnTouchListener((v, event) -> true);
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
                        setItemsExperience(position);
                    }
                }
            }
        });

        // Experience layout
        if (items.get(0) instanceof Work) {
            layoutExperience.setVisibility(View.VISIBLE);
            setItemsExperience(0);
        }

        // Scroll left on arrow click
        buttonPreviousItem.setOnClickListener(v -> {
            int firstVisible = layoutManager.findFirstVisibleItemPosition();
            recyclerView.smoothScrollToPosition(Math.max(0, firstVisible - 1));
        });

        // Scroll right on arrow click
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