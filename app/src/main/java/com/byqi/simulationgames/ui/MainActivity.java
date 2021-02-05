package com.byqi.simulationgames.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byqi.simulationgames.R;
import com.byqi.simulationgames.adapter.GameListAdapter;

public class MainActivity extends Activity {
    RecyclerView gameList;
    String[] gameNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameNames = getResources().getStringArray(R.array.game_list_names);

        GameListAdapter gameListAdapter = new GameListAdapter(gameNames);
        gameList = (RecyclerView) findViewById(R.id.game_list);
        gameList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        gameList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        gameList.setAdapter(gameListAdapter);

    }
}
