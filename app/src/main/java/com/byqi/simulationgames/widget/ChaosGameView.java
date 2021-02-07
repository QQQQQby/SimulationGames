package com.byqi.simulationgames.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import androidx.annotation.Nullable;

import com.byqi.simulationgames.R;
import com.byqi.simulationgames.model.ChaosGame;

public class ChaosGameView extends View {

    int width, height, gameViewWidth, gameViewHeight, paddingLeft, paddingRight, paddingTop, paddingBottom;
    final ChaosGame game;
    Paint paint;

    public ChaosGameView(Context context) {
        this(context, null, 0);
    }

    public ChaosGameView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChaosGameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ChaosGameView);
        gameViewWidth = ta.getInteger(R.styleable.ChaosGameView_game_width, 400);
        gameViewHeight = ta.getInteger(R.styleable.ChaosGameView_game_height, 800);
        ta.recycle();

        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();

        width = gameViewWidth + paddingLeft + paddingRight;
        height = gameViewHeight + paddingTop + paddingBottom;

        game = new ChaosGame();
        paint = new Paint();

        //Test
        game.setA(new Pair<>(0, gameViewHeight / 2));
        game.setB(new Pair<>(600, 0));
        game.setC(new Pair<>(800, 1600));
        game.setP(new Pair<>(gameViewWidth / 2.0, gameViewHeight / 2.0));
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (game) {
                    game.updateP();
                }
                postInvalidate();
            }
        }).start();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec) + paddingLeft + paddingRight;
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec) + paddingTop + paddingBottom;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (game) {
            paint.setStrokeWidth(1);
            paint.setColor(Color.RED);
            Pair<Integer, Integer> A = game.getA(), B = game.getB(), C = game.getC();
            if (A != null)
                canvas.drawPoint(A.first + paddingLeft, A.second + paddingTop, paint);
            if (B != null)
                canvas.drawPoint(B.first + paddingLeft, B.second + paddingTop, paint);
            if (C != null)
                canvas.drawPoint(C.first + paddingLeft, C.second + paddingTop, paint);

            paint.setColor(Color.BLUE);
            Pair<Double, Double> P = game.getP();
            if (P != null)
                canvas.drawPoint(P.first.intValue() + paddingLeft, P.second.intValue() + paddingTop, paint);

            paint.setColor(Color.BLACK);
            for (Pair<Integer, Integer> point : game.getHistory())
                canvas.drawPoint(point.first + paddingLeft, point.second + paddingTop, paint);
        }
    }
}
