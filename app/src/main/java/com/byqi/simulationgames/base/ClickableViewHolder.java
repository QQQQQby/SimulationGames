package com.byqi.simulationgames.base;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public abstract class ClickableViewHolder extends RecyclerView.ViewHolder {

    public ClickableViewHolder(View view, final OnItemClickListener onItemClickListener) {
        super(view);
        view.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onButtonClicked(view, position);
                }
            }
        });
    }
}
