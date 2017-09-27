package com.yao.hancoder.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yao.hancoder.R;

import java.util.Locale;

/**
 * Created by heyao on 9/15/17.
 */

public class CanvasTextView extends View {

    private Paint mTextPaint;
    private String text = "Hello HenCoder";
    private String text1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
    private String text2 = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz";
    private String text3 = "大雨落幽燕，白浪滔天";
    private String text4 = "Hello HenCoder \uD83C\uDDE8\uD83C\uDDF3";
    private StaticLayout staticLayout1, staticLayout2;
    private Rect rect;
    private Paint paint;
    private String TAG = "CanvasTextView";

    public CanvasTextView(Context context) {
        super(context);
        init();
    }

    public CanvasTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(64);
        mTextPaint.setShadowLayer(10, 5, 5, getResources().getColor(R.color.color1));

        TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(36);
        staticLayout1 = new StaticLayout(text1, paint, 600, Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
        staticLayout2 = new StaticLayout(text2, paint, 600, Layout.Alignment.ALIGN_NORMAL, 1, 0, true);

        rect = new Rect();

        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setTextSize(64);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mTextPaint.setFakeBoldText(false);
//        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextLocale(Locale.CHINA);
        canvas.drawText(text3, 200, 300, mTextPaint);
//        mTextPaint.setFakeBoldText(true);
//        mTextPaint.setTextSkewX(0.5f);
//        mTextPaint.setTextScaleX(0.6f);
//        mTextPaint.setLetterSpacing(-0.2f);
//        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextLocale(Locale.JAPAN);
        canvas.drawText(text3, 200, 400 + mTextPaint.getFontSpacing(), mTextPaint);

        float textWidth = mTextPaint.measureText(text3);
        mTextPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
//        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setStrokeWidth(2f);
        canvas.drawLine(200, 400 + mTextPaint.getFontSpacing(), 200 + textWidth,
                400 + mTextPaint.getFontSpacing(), mTextPaint);

//            mTextPaint.setLetterSpacing(0.2f);
//        mTextPaint.setTextAlign(Paint.Align.RIGHT);
//        mTextPaint.setTextScaleX(1.8f);
        mTextPaint.setTextLocale(Locale.TAIWAN);
        canvas.drawText(text3, 200, 500 + mTextPaint.getFontSpacing() * 2, mTextPaint);

        mTextPaint.getTextBounds(text3, 0, text3.length() - 3, rect);
        rect.left += 200;
        rect.top += 500 + mTextPaint.getFontSpacing() * 2;
        rect.right += 200;
        rect.bottom += 500 + mTextPaint.getFontSpacing() * 2;

        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawRect(rect, mTextPaint);


        canvas.save();
        canvas.translate(50, 50);
        staticLayout1.draw(canvas);
        canvas.translate(0, 200);
        staticLayout2.draw(canvas);
        canvas.restore();

        int measuredCount;
        float[] measuredWidth = {0};
        int length = text.length();
        measuredCount = paint.breakText(text, 0, length, true, 300, measuredWidth);
        Log.e(TAG, "measuredCount: " + measuredCount + "\t measuredWidth: " + measuredWidth[0]);
        canvas.drawText(text, 0, measuredCount, 10, 900, paint);

        float fontSpacing = this.paint.getFontSpacing();
        measuredCount = paint.breakText(text, 0, length, true, 400, measuredWidth);
        Log.e(TAG, "measuredCount: " + measuredCount + "\t measuredWidth: " + measuredWidth[0]);
        canvas.drawText(text, 0, measuredCount, 10, 900 + fontSpacing, this.paint);

        /*measuredCount = paint.breakText(text, 0, length, true, 500, measuredWidth);
        Log.e(TAG, "measuredCount: " + measuredCount + "\t measuredWidth: " + measuredWidth[0]);
        canvas.drawText(text, 0, measuredCount, 10, 900 + fontSpacing * 2, this.paint);

        measuredCount = paint.breakText(text, 0, length, true, 600, measuredWidth);
        Log.e(TAG, "measuredCount: " + measuredCount + "\t measuredWidth: " + measuredWidth[0]);
        canvas.drawText(text, 0, measuredCount, 10, 900 + fontSpacing * 3, this.paint);*/

        int length1 = text4.length();
        float advance = this.paint.getRunAdvance(text4, 0, length1, 0, length1, false, length1);
        canvas.drawText(text4, 10, 1100, this.paint);
        canvas.drawLine(10 + advance, 1050, 10 + advance, 1110, this.paint);

        float spacing = this.paint.getFontSpacing();

        float advance1 = this.paint.getRunAdvance(text4, 0, length1, 0, length1, false, length1 - 1);
        canvas.drawText(text4, 10, 1100 + spacing, this.paint);
        canvas.drawLine(10 + advance1, 1050 + spacing, 10 + advance1, 1110 + spacing, this.paint);

        float advance2 = this.paint.getRunAdvance(text4, 0, length1, 0, length1, false, length1 - 2);
        canvas.drawText(text4, 10, 1100 + spacing * 2, this.paint);
        canvas.drawLine(10 + advance2, 1050 + spacing * 2, 10 + advance2, 1110 + spacing * 2, this.paint);

        float advance3 = this.paint.getRunAdvance(text4, 0, length1, 0, length1, false, length1 - 3);
        canvas.drawText(text4, 10, 1100 + spacing * 3, this.paint);
        canvas.drawLine(10 + advance3, 1050 + spacing * 3, 10 + advance3, 1110 + spacing * 3, this.paint);

        float advance4 = this.paint.getRunAdvance(text4, 0, length1, 0, length1, false, length1 - 4);
        canvas.drawText(text4, 10, 1100 + spacing * 4, this.paint);
        canvas.drawLine(10 + advance4, 1050 + spacing * 4, 10 + advance4, 1110 + spacing * 4, this.paint);

        float advance5 = this.paint.getRunAdvance(text4, 0, length1, 0, length1, false, length1 - 5);
        canvas.drawText(text4, 10, 1100 + spacing * 5, this.paint);
        canvas.drawLine(10 + advance5, 1050 + spacing * 5, 10 + advance5, 1110 + spacing * 5, this.paint);
        Log.e(TAG, "advance: " + advance);
        Log.e(TAG, "advance1: " + advance1);
        Log.e(TAG, "advance2: " + advance2);
        Log.e(TAG, "advance3: " + advance3);
        Log.e(TAG, "advance4: " + advance4);
        Log.e(TAG, "advance5: " + advance5);

        int offsetForAdvance = paint.getOffsetForAdvance(text4, 0, length1, 0, length1, false, advance5);
        Log.e(TAG, "onDraw: " + offsetForAdvance);
        canvas.drawText(text4, 0, offsetForAdvance, 10, 1100 + spacing * 6, this.paint);
    }
}
