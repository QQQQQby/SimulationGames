package com.byqi.simulationgames.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class GameOfLifeViewGroup  extends ViewGroup {
    public GameOfLifeViewGroup(Context context) {
        this(context, null, 0);
    }

    public GameOfLifeViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameOfLifeViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
