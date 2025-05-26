package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.adapter.ItemsAdapter;
import com.scipath.becomeaking.adapter.OnItemBoughtListener;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.viewmodel.PersonageViewModel;

import java.util.List;


public class ItemsFragment extends Fragment {

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

        // Getting personage and items from Application
        Personage personage = BecomeAKing.getInstance().getCurrentPersonage();
        Bundle args = getArguments();
        int categoryId = args.getInt("categoryId");

        // Getting ViewModel from the Activity
        PersonageViewModel personageViewModel =
                new ViewModelProvider(requireActivity()).get(PersonageViewModel.class);

        // Views
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_items);
        ImageView arrowLeft = view.findViewById(R.id.arrow_left);
        ImageView arrowRight = view.findViewById(R.id.arrow_right);
        Button buttonBack = view.findViewById(R.id.button_back);

        // Adapter
        ItemsAdapter itemsAdapter = new ItemsAdapter(categoryId, new OnItemBoughtListener() {
            @Override
            public void update() {
                personageViewModel.setPersonage(personage);
            }
        }, view.getContext());

        // Displaying items
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemsAdapter);

        // Scroll left on arrow click
        arrowLeft.setOnClickListener(v -> {
            int firstVisible = layoutManager.findFirstVisibleItemPosition();
            recyclerView.smoothScrollToPosition(Math.max(0, firstVisible - 1));
        });

        // Scroll right on arrow click
        arrowRight.setOnClickListener(v -> {
            int lastVisible = layoutManager.findLastVisibleItemPosition();
            recyclerView.smoothScrollToPosition(lastVisible + 1);
        });

        // Button back
        buttonBack.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }
}