package com.yao.hancoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by heyao on 2017/8/15.
 */

public class BezierView extends View {

    private static final String TAG = "BezierView";
    private Paint mPaint;
    private int centerX, centerY;
    private Point mStartPoint, mEndPoint, mControlPoint;
    private Path mPath;

    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setTextSize(60);

        mStartPoint = new Point(0, 0);
        mEndPoint = new Point(0, 0);
        mControlPoint = new Point(0, 0);
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: ");
        //初始化起始点、控制点
        centerX = w / 2;
        centerY = h / 2;

        mStartPoint.x = centerX - 200;
        mStartPoint.y = centerY;

        mEndPoint.x = centerX + 200;
        mEndPoint.y = centerY;

        mControlPoint.x = centerX;
        mControlPoint.y = centerY - 200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(mStartPoint.x, mStartPoint.y, mPaint);
        canvas.drawPoint(mEndPoint.x, mEndPoint.y, mPaint);
        canvas.drawPoint(mControlPoint.x, mControlPoint.y, mPaint);

        mPaint.setStrokeWidth(4);
        canvas.drawLine(mStartPoint.x, mStartPoint.y, mControlPoint.x, mControlPoint.y, mPaint);
        canvas.drawLine(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y, mPaint);

        mPaint.setColor(Color.RED);
        mPath.reset();
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPath.quadTo(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mControlPoint.x = (int) event.getX();
        mControlPoint.y = (int) event.getY();
        invalidate();
        return true;
    }
}
