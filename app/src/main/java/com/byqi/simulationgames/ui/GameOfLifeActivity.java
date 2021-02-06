package com.byqi.simulationgames.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.byqi.simulationgames.R;
import com.byqi.simulationgames.model.GameOfLife;
import com.byqi.simulationgames.widget.GameOfLifeView;

public class GameOfLifeActivity extends Activity {

    private ImageButton startButton, pauseButton, clearButton;
    RelativeLayout startButtonLayout, pauseButtonLayout;
    GameOfLifeView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_of_life_activity);

        startButton = findViewById(R.id.game_of_life_start_btn);
        pauseButton = findViewById(R.id.game_of_life_pause_btn);
        clearButton = findViewById(R.id.game_of_life_clear_btn);
        startButtonLayout=findViewById(R.id.game_of_life_start_btn_layout);
        pauseButtonLayout=findViewById(R.id.game_of_life_pause_btn_layout);
        gameView = findViewById(R.id.game_of_life_view);

        startButton.setOnClickListener(v -> {
            startButtonLayout.setVisibility(View.GONE);
            pauseButtonLayout.setVisibility(View.VISIBLE);
            gameView.setPaused(false);
        });
        pauseButton.setOnClickListener(v -> {
            startButtonLayout.setVisibility(View.VISIBLE);
            pauseButtonLayout.setVisibility(View.GONE);
            gameView.setPaused(true);
        });
        clearButton.setOnClickListener(v -> {
            startButtonLayout.setVisibility(View.VISIBLE);
            pauseButtonLayout.setVisibility(View.GONE);
            gameView.setPaused(true);
            gameView.clear();
        });

        pauseButtonLayout.setVisibility(View.GONE);
    }
}
