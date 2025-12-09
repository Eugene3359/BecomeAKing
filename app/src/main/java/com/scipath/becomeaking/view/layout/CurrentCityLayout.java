package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.activity.BaseActivity;
import com.scipath.becomeaking.view.activity.MapActivity;
import com.scipath.becomeaking.view.fragment.DialogueFragment;
import com.scipath.becomeaking.viewmodel.CurrentCityViewModel;


public class CurrentCityLayout extends LinearLayout {

    private ImageView imageView;
    private Button buttonCity;
    private Button buttonMap;


    public CurrentCityLayout(Context context) {
        super(context);
        init(context);
    }

    public CurrentCityLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CurrentCityLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_current_city, this);
        imageView = findViewById(R.id.image_view);
        buttonCity = findViewById(R.id.button_city);
        buttonMap = findViewById(R.id.button_map);
    }

    private void init(Context context, AttributeSet attrs) {
        init(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CurrentCityLayout);
        setText(typedArray.getResourceId(R.styleable.CurrentCityLayout_text, R.string.placeholder), context);
        setImageResource(typedArray.getResourceId(R.styleable.CurrentCityLayout_src, R.drawable.item_placeholder));
    }

    public void bind(LifecycleOwner owner) {
        if (!(getContext() instanceof BaseActivity)) return;
        BaseActivity activity = (BaseActivity) getContext();

        CurrentCityViewModel viewModel =
                new ViewModelProvider(
                        BecomeAKing.getInstance(),
                        ViewModelProvider.AndroidViewModelFactory.getInstance(BecomeAKing.getInstance())
                ).get(CurrentCityViewModel.class);

        viewModel.getCity().observe(owner, city -> {
            setImageResource(city.getImageId());
            setText(city.getNameId(), activity);

            if (BecomeAKing.getInstance().isTraveling()) {
                buttonCity.setOnClickListener(null);
            } else {
                DialogueFragment dialogue = new DialogueFragment.Builder()
                        .setHeader(city.getNameId())
                        .setMessage(city.getDescriptionId())
                        .setButton1(R.string.got_it, null)
                        .build();
                buttonCity.setOnClickListener(v -> {
                    activity.showDialogue(dialogue);
                });
            }
        });

        buttonMap.setOnClickListener(v -> {
            Intent intent = new Intent(activity, MapActivity.class);
            activity.startActivity(intent);
        });
    }

    public void setText(int resId, Context context) {
        buttonCity.setText(resId);
        imageView.setContentDescription(context.getText(resId));
    }

    public void setImageResource(int resId) {
        imageView.setImageResource(resId);
    }

    public void setButtonCityOnClickListener(OnClickListener listener) {
        buttonCity.setOnClickListener(listener);
    }

    public void setButtonMapOnClickListener(OnClickListener listener) {
        buttonMap.setOnClickListener(listener);
    }
}
