package com.scipath.becomeaking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IGood;
import com.scipath.becomeaking.contract.model.IGoods;
import com.scipath.becomeaking.contract.model.IPersonage;
import com.scipath.becomeaking.model.Good;
import com.scipath.becomeaking.model.enums.GoodType;
import com.scipath.becomeaking.view.activity.GameActivity;
import com.scipath.becomeaking.view.view.CustomButton;


public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    // Variables
    IGoods goods;
    GameActivity context;


    // Constructor
    public GoodsAdapter(IGoods goods, GameActivity context) {
        this.goods = goods;
        this.context = context;
    }


    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout layout;
        private final ImageView image;
        private final TextView textName1;
        private final TextView textName2;
        private final TextView textBuyPrice;
        private final TextView textSellPrice;
        private final CustomButton buttonBuy;
        private final CustomButton buttonSell;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            image = layout.findViewById(R.id.image_good);
            textName1 = layout.findViewById(R.id.text_name1);
            textName2 = layout.findViewById(R.id.text_name2);
            textBuyPrice = layout.findViewById(R.id.text_buy_price);
            textSellPrice = layout.findViewById(R.id.text_sell_price);
            buttonBuy = layout.findViewById(R.id.button_buy);
            buttonSell = layout.findViewById(R.id.button_sell);
        }


        public LinearLayout getLayout() {
            return layout;
        }

        public CustomButton getButtonBuy() {
            return buttonBuy;
        }

        public CustomButton getButtonSell() {
            return buttonSell;
        }

        public void bind(IGood good, Context context) {
            image.setImageResource(good.getType().imageId);
            image.setContentDescription(context.getString(good.getType().nameId));
            textName1.setText(good.getType().nameId);
            textName2.setText(good.getType().nameId);
            textBuyPrice.setText(String.valueOf(good.getBuyPrice()));
            textSellPrice.setText(String.valueOf(good.getSellPrice()));
        }

        public void refreshButtonsStates(IGood good, IPersonage personage, IGoods storage, Context context) {
            if (good.getAmount() == 0 ||
                personage.getMoney() < good.getBuyPrice()) {
                buttonBuy.setBackgroundColor(context.getColor(R.color.placeholder));
            } else {
                buttonBuy.setBackgroundColor(context.getColor(R.color.goods_button_green));
            }

            if (storage.get(good.getType()) == null) {
                buttonSell.setBackgroundColor(context.getColor(R.color.placeholder));
            } else {
                buttonSell.setBackgroundColor(context.getColor(R.color.goods_button_red));
            }
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_good, parent, false);
        return new GoodsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IGood good = goods.get(position);
        holder.bind(good, context);

        GoodType type = good.getType();
        IPersonage personage = BecomeAKing.getInstance().getPersonage();
        IGoods storage = BecomeAKing.getInstance().getGoodsStorage();
        holder.refreshButtonsStates(good, personage, storage, context);

        // Button Buy
        holder.getButtonBuy().setOnClickListener(view -> {
            if (good.getAmount() == 0) {
                context.showNotification(R.string.no_good_left);
            } else if (personage.getMoney() < good.getBuyPrice()) {
                context.showNotification(R.string.not_enough_money);
            } else {
                personage.affectMoney(-good.getBuyPrice());
                context.updateMoney();
                good.affectAmount(-1);
                if (storage.get(type) == null) {
                    storage.add(new Good(type, 1));
                } else {
                    storage.get(type).affectAmount(1);
                }
                holder.refreshButtonsStates(good, personage, storage, context);
            }
        });

        // Button Sell
        holder.getButtonSell().setOnClickListener(view -> {
            if (storage.get(type) == null) {
                context.showNotification(R.string.you_dont_have_this_good);
            } else {
                personage.affectMoney(good.getSellPrice());
                context.updateMoney();
                good.affectAmount(1);
                storage.get(type).affectAmount(-1);
                if (storage.get(type).getAmount() == 0) {
                    storage.remove(type);
                }
                holder.refreshButtonsStates(good, personage, storage, context);
            }
        });
    }


    @Override
    public int getItemCount() {
        return goods.size();
    }
}
