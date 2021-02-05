package com.byqi.simulationgames.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.byqi.simulationgames.R;
import com.byqi.simulationgames.model.GameOfLife;

public class GameOfLifeView extends View {

    private int row, col, cellWidth, cellHeight;
    private int paddingLeft, paddingRight, paddingTop, paddingBottom, width, height;

    private GameOfLife game;

    public GameOfLifeView(Context context) {
        this(context, null, 0);
    }

    public GameOfLifeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameOfLifeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GameOfLifeView);
        row = ta.getInteger(R.styleable.GameOfLifeView_row, 200);
        col = ta.getInteger(R.styleable.GameOfLifeView_col, 100);
        cellWidth = ta.getInteger(R.styleable.GameOfLifeView_cell_width, 5);
        cellHeight = ta.getInteger(R.styleable.GameOfLifeView_cell_height, 5);
        ta.recycle();

        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();

        width = cellWidth * col + paddingLeft + paddingRight;
        height = cellHeight * row + paddingTop + paddingBottom;

        game = new GameOfLife(row, col);
    }

    public void update() {
        synchronized (game) {

        }
    }

}
