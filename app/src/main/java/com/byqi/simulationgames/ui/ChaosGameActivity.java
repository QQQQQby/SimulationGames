package com.byqi.simulationgames.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.byqi.simulationgames.R;
import com.byqi.simulationgames.widget.ChaosGameView;

public class ChaosGameActivity extends Activity {

    TextView title;
    ChaosGameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chaos_game_activity);

        title = findViewById(R.id.chaos_game_title).findViewById(R.id.title_name);
        title.setText(getResources().getString(R.string.chaos_game));

        gameView = findViewById(R.id.chaos_game_view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameView.stop();
    }
}
