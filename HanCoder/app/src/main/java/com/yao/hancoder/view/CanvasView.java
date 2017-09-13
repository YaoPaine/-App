package com.yao.hancoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yao.hancoder.R;

/**
 * Created by heyao on 2017/8/5.
 */

public class CanvasView extends View {
    private Context context;
    private Paint mPaint;
    private Path mPath;

    public CanvasView(Context context) {
        super(context);
        initPaint(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    /**
     * Paint.setStyle(Style style) 设置绘制模式
     * Paint.setColor(int color) 设置颜色
     * Paint.setStrokeWidth(float width) 设置线条宽度
     * Paint.setTextSize(float textSize) 设置文字大小
     * Paint.setAntiAlias(boolean aa) 设置抗锯齿开关
     */
    private void initPaint(Context context) {
        this.context = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.BLACK);//这类颜色填充方法一般用于在绘制之前设置底色，或者在绘制之后为界面设置半透明蒙版
//        canvas.drawCircle(100, 100, 50, mPaint);
//        canvas.drawRGB(100, 100, 100);
//        canvas.drawColor(Color.BLACK);
        canvas.drawColor(Color.parseColor("#88880000"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        canvas.drawCircle(100, 100, 50, mPaint);

        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(200, 200, 300, 300, mPaint);

        mPaint.setStrokeWidth(30);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置点的形状，round（圆形）、butt（方形）、square（方形）
        canvas.drawPoint(100, 100, mPaint);
        canvas.drawPoint(100, 200, mPaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawOval(200, 100, 350, 200, mPaint);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawOval(400, 50, 700, 200, mPaint);
        }

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(100, 100, 300, 300, mPaint);

        float[] lines = {20, 400, 120, 400, 70, 400, 70, 500, 20, 500, 120, 500};
        canvas.drawLines(lines, mPaint);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(20, 600, 220, 700, 10, 100, mPaint);
        }

        mPaint.setStyle(Paint.Style.FILL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawArc(30, 800, 330, 1000, -120, 100, true, mPaint);
            canvas.drawArc(30, 800, 330, 1000, 10, 150, false, mPaint);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(30, 800, 330, 1000, 180, 50, false, mPaint);
        }

        mPath.addCircle(400, 400, 100, Path.Direction.CW);
        mPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mPath, mPaint);


        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(context.getResources().getColor(R.color.colorAccent));
        mPath.lineTo(300, 700);
        mPath.rLineTo(100, 0);
        canvas.drawPath(mPath, mPaint);

        mPaint.setTextSize(18);
        canvas.drawText("春天花卉开", 400, 400, mPaint);

        mPaint.setTextSize(36);
        canvas.drawText("鸟儿自由自在", 500, 500, mPaint);

        mPaint.setStrokeWidth(10);
        mPaint.setTextSize(64);
        canvas.drawText("鸟儿自由自在", 600, 600, mPaint);
    }
}
