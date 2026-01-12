package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.adapter.GoodsAdapter;
import com.scipath.becomeaking.data.GoodsList;
import com.scipath.becomeaking.model.Goods;
import com.scipath.becomeaking.view.activity.GameActivity;
import com.scipath.becomeaking.view.layout.CurrentCityLayout;


public class MarketFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showInDevelopmentNotification();

        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        CurrentCityLayout currentCityLayout = view.findViewById(R.id.layout_current_city);
        currentCityLayout.bind(getViewLifecycleOwner());

        Goods goods = GoodsList.getDefaultGoods();
        RecyclerView recyclerView = view.findViewById(R.id.goods_list);
        GoodsAdapter goodsAdapter = new GoodsAdapter(goods, (GameActivity) requireActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(goodsAdapter);
    }
}
