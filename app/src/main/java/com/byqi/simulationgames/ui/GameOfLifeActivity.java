package com.byqi.simulationgames.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.byqi.simulationgames.R;

public class GameOfLifeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_of_life_activity);
    }
}
