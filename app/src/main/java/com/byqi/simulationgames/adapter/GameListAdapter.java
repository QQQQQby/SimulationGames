package com.byqi.simulationgames.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byqi.simulationgames.R;
import com.byqi.simulationgames.base.ClickableViewHolder;
import com.byqi.simulationgames.base.OnItemClickListener;

import java.util.Arrays;
import java.util.List;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameListItemViewHolder> {

    List<String> gameNames;
    OnItemClickListener onItemClickListener;

    public GameListAdapter(String[] gameNames) {
        this.gameNames = Arrays.asList(gameNames);
    }

    @NonNull
    @Override
    public GameListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        return new GameListItemViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GameListItemViewHolder holder, int position) {
        holder.gameNameView.setText(gameNames.get(position));
    }

    @Override
    public int getItemCount() {
        return gameNames.size();
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.onItemClickListener = clickListener;
    }

    static class GameListItemViewHolder extends ClickableViewHolder {
        public final TextView gameNameView;

        GameListItemViewHolder(View view, final OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
            gameNameView = view.findViewById(R.id.game_list_item_name);
        }
    }

}

