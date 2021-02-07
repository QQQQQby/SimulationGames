package com.byqi.simulationgames.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.byqi.simulationgames.R;
import com.byqi.simulationgames.widget.GameOfLifeView;

public class GameOfLifeActivity extends Activity {

    GameOfLifeView gameView;
    ImageButton startButton, pauseButton, clearButton;
    RelativeLayout startButtonLayout, pauseButtonLayout;
    SeekBar speedSeekBar;
    TextView title, speedTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_of_life_activity);

        title = findViewById(R.id.game_of_life_title).findViewById(R.id.title_name);
        gameView = findViewById(R.id.game_of_life_view);
        startButton = findViewById(R.id.game_of_life_start_btn);
        pauseButton = findViewById(R.id.game_of_life_pause_btn);
        clearButton = findViewById(R.id.game_of_life_clear_btn);
        startButtonLayout = findViewById(R.id.game_of_life_start_btn_layout);
        pauseButtonLayout = findViewById(R.id.game_of_life_pause_btn_layout);
        speedSeekBar = findViewById(R.id.game_of_life_speed_seek_bar);
        speedTextView = findViewById(R.id.game_of_life_speed_text_view);

        title.setText(getResources().getString(R.string.game_of_life));

        startButton.setOnClickListener(v -> {
            startButtonLayout.setVisibility(View.GONE);
            pauseButtonLayout.setVisibility(View.VISIBLE);
            gameView.resume();
        });
        pauseButton.setOnClickListener(v -> {
            startButtonLayout.setVisibility(View.VISIBLE);
            pauseButtonLayout.setVisibility(View.GONE);
            gameView.pause();
        });
        clearButton.setOnClickListener(v -> {
            startButtonLayout.setVisibility(View.VISIBLE);
            pauseButtonLayout.setVisibility(View.GONE);
            gameView.pause();
            gameView.clear();
        });
        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                speedTextView.setText(getResources().getString(R.string.speed) + (i + 1));
                gameView.setSpeed(i + 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        pauseButtonLayout.setVisibility(View.GONE);
        speedTextView.setText(speedTextView.getText() + "1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        startButtonLayout.setVisibility(View.VISIBLE);
        pauseButtonLayout.setVisibility(View.GONE);
        gameView.pause();
    }
}
