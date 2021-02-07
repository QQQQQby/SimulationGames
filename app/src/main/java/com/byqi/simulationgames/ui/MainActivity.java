package com.byqi.simulationgames.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byqi.simulationgames.R;
import com.byqi.simulationgames.adapter.GameListAdapter;

public class MainActivity extends Activity {
    RecyclerView gameList;
    String[] gameNames;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        title = findViewById(R.id.main_activity_title).findViewById(R.id.title_name);
        title.setText(getResources().getString(R.string.simulation_games));

        gameNames = getResources().getStringArray(R.array.game_list_names);

        GameListAdapter gameListAdapter = new GameListAdapter(gameNames);
        gameListAdapter.setOnItemClickListener((view, position) -> {
            Class<?> activityClass;
            Log.d("hh",position+"hhhh");
            switch (position) {
                case 0:
                    activityClass = GameOfLifeActivity.class;
                    break;
                case 1:
                    activityClass = ChaosGameActivity.class;
                    break;
                default:
                    throw new IllegalStateException("Unexpected position: " + position);
            }
            if (activityClass == null)
                Toast.makeText(getApplicationContext(), "Failed to open", Toast.LENGTH_SHORT).show();
            else
                startActivity(new Intent(MainActivity.this, activityClass));
        });

        gameList = findViewById(R.id.game_list);
        gameList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        gameList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        gameList.setAdapter(gameListAdapter);

    }
}
