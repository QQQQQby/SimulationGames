package com.byqi.simulationgames.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.byqi.simulationgames.R;
import com.byqi.simulationgames.model.GameOfLife;

public class GameOfLifeView extends View implements View.OnTouchListener {

    private final int row, col, cellWidth, cellHeight;
    private final int paddingLeft, paddingRight, paddingTop, paddingBottom, width, height;
    private final Paint paint;
    private final GameOfLife game;
    private boolean paused;

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

        paint = new Paint();
        setOnTouchListener(this);

        paused = true;
        startUpdate();
    }

    public void pause() {
        synchronized (game) {
            this.paused = true;
        }
    }

    public void resume() {
        synchronized (game) {
            this.paused = false;
            game.notify();
        }
    }

    public void clear() {
        synchronized (game) {
            game.clearCells();
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(1);
        for (Pair<Integer, Integer> cell : game.getCells()) {
            Pair<Integer, Integer> position = cellCoordinateToPosition(cell);
            canvas.drawRect(position.first, position.second, position.first + cellWidth - 1, position.second + cellHeight - 1, paint);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Pair<Integer, Integer> cell = positionToCellCoordinate((int) (event.getX()), (int) (event.getY()));
        Log.d("touch", (int) (event.getX()) + ", " + (int) (event.getY()));
        if (cell.first < 0 || cell.first >= row || cell.second < 0 || cell.second >= col)
            return true;
        Log.d("cell", cell.first + ", " + cell.second);
        synchronized (game) {
            game.addCell(cell);
            postInvalidate();
        }
        return true;
    }

    /**
     * @param coordinate, x and y
     * @return the top left pixel of the cell
     */
    private Pair<Integer, Integer> cellCoordinateToPosition(Pair<Integer, Integer> coordinate) {
        return new Pair<>(paddingLeft + coordinate.second * cellWidth, paddingTop + coordinate.first * cellHeight);
    }

    private Pair<Integer, Integer> positionToCellCoordinate(int x, int y) {
        return new Pair<>((y - paddingTop) / cellHeight, (x - paddingLeft) / cellWidth);
    }

    private void startUpdate() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (game) {
                    if (paused) {
                        try {
                            game.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    game.updateCells();
                    postInvalidate();
                }
            }
        }).start();
    }
}
