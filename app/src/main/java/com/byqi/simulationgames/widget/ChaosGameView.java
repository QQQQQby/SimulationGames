package com.byqi.simulationgames.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.byqi.simulationgames.R;
import com.byqi.simulationgames.model.ChaosGame;

public class ChaosGameView extends View {

    int width, height, gameWidth, gameHeight, paddingLeft, paddingRight, paddingTop, paddingBottom;
    final ChaosGame game;
    Paint paint;
    boolean stopped;

    public ChaosGameView(Context context) {
        this(context, null, 0);
    }

    public ChaosGameView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChaosGameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ChaosGameView);
        gameWidth = ta.getInteger(R.styleable.ChaosGameView_game_width, 400);
        gameHeight = ta.getInteger(R.styleable.ChaosGameView_game_height, 800);
        ta.recycle();

        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();

        width = gameWidth + paddingLeft + paddingRight;
        height = gameHeight + paddingTop + paddingBottom;

        game = new ChaosGame();
        paint = new Paint();
        stopped = false;

        //Test
        game.addPointToPolygon(new Pair<>(100, 100));
        game.addPointToPolygon(new Pair<>(900, 500));
        game.addPointToPolygon(new Pair<>(300, 1500));
        game.setP(new Pair<>(0.0, 0.0));
        new Thread(() -> {
            while (!stopped) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (game) {
                    game.updateP();
                    invalidate();
                }
            }
        }).start();


        setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                synchronized (game) {
                    game.addPointToPolygon(new Pair<>((int) (motionEvent.getX()), (int) (motionEvent.getY())));
                    invalidate();
                }
            }
            performClick();
            return true;
        });

    }

    public void stop() {
        stopped = true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
            gameWidth = width - paddingLeft - paddingRight;
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec);
            gameHeight = height - paddingTop - paddingBottom;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (game) {
            paint.setStrokeWidth(1);
            paint.setColor(Color.RED);
            for (Pair<Integer, Integer> point : game.getPolygon())
                canvas.drawCircle(point.first + paddingLeft, point.second + paddingTop, 10, paint);

            paint.setColor(Color.BLUE);
            Pair<Double, Double> P = game.getP();
            if (P != null)
                canvas.drawCircle(P.first.intValue() + paddingLeft, P.second.intValue() + paddingTop, 5, paint);

            paint.setColor(Color.BLACK);
            for (Pair<Integer, Integer> point : game.getHistory())
                canvas.drawPoint(point.first + paddingLeft, point.second + paddingTop, paint);

            canvas.drawLine(0, 0, width, 0, paint);
            canvas.drawLine(width - 1, 0, width - 1, height, paint);
            canvas.drawLine(width - 1, height - 1, 0, height - 1, paint);
            canvas.drawLine(0, height - 1, 0, 0, paint);

//            canvas.drawLine(paddingLeft, paddingTop, paddingLeft + gameWidth, paddingTop, paint);
//            canvas.drawLine(paddingLeft + gameWidth, paddingTop, paddingLeft + gameWidth, paddingTop + gameHeight, paint);
//            canvas.drawLine(paddingLeft + gameWidth, paddingTop + gameHeight, paddingLeft, paddingTop + gameHeight, paint);
//            canvas.drawLine(paddingLeft, paddingTop + gameHeight, paddingLeft, paddingTop, paint);
        }
    }

}
