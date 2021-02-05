package com.byqi.simulationgames.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byqi.simulationgames.R;

import java.util.Arrays;
import java.util.List;

public class GameListAdapter extends RecyclerView.Adapter<GameListItemViewHolder> {

    List<String> gameNames;

    public GameListAdapter(String[] gameNames) {
        this.gameNames = Arrays.asList(gameNames);
    }

    @NonNull
    @Override
    public GameListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        return new GameListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameListItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return gameNames.size();
    }
}

class GameListItemViewHolder extends RecyclerView.ViewHolder {
    public final TextView gameNameView;

    GameListItemViewHolder(View view) {
        super(view);
        gameNameView = (TextView) view.findViewById(R.id.game_list_item_name);
    }

}