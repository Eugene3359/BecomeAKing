package com.scipath.becomeaking.view.activity;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.scipath.becomeaking.manager.LocaleManager;


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.wrap(newBase));
    }
}
