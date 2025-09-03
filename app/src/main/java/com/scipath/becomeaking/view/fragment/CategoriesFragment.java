package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.adapter.CategoriesAdapter;
import com.scipath.becomeaking.contract.model.ICategory;

import java.util.ArrayList;


public class CategoriesFragment extends BaseFragment {

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Getting sublist of categories
        Bundle args = getArguments();
        ArrayList<ICategory> categories = BecomeAKing.getInstance()
                .getCategoriesSublist(args.getInt("fromIndex"), args.getInt("toIndex"));

        // View
        RecyclerView recyclerView = view.findViewById(R.id.category_list);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categories, view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(categoriesAdapter);
    }
}