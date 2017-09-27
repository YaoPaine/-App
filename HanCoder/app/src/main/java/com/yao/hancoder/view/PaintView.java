package com.yao.hancoder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yao.hancoder.R;

/**
 * Created by heyao on 9/11/17.
 */

public class PaintView extends View {
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private RadialGradient mRadialGradient;
    private SweepGradient mSweepGradient;
    private Context context;
    private BitmapShader mBitmapShader;
    private Bitmap sourceImage;
    private Bitmap destinationImage;
    private PorterDuffXfermode porterDuffXfermode;
    private BitmapShader sourceBitmapShader;
    private BitmapShader destinationbitmapShader;
    private ComposeShader composeShader;
    private String TAG = "PaintView";
    private int height;
    private int width;
    private Bitmap bitmap;
    private PorterDuffXfermode xDuffXfermode;
    private Bitmap bitmapZ;
    private static final float UNDERLINE_CLEAR_GAP = 5.5f;

    public PaintView(Context context) {
        super(context);
        init(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //x0 y0 x1 y1：渐变的两个端点的位置
        //color0 color1 是端点的颜色
//        mLinearGradient = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        /*centerX centerY：辐射中心的坐标
        radius：辐射半径
        centerColor：辐射中心的颜色
        edgeColor：辐射边缘的颜色
        tileMode：辐射范围之外的着色模式。*/
//        mRadialGradient = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        /*cx cy ：扫描的中心
        color0：扫描的起始颜色
        color1：扫描的终止颜色*/
//        mSweepGradient = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"));

//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.xong);
//        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        sourceImage = BitmapFactory.decodeResource(context.getResources(), R.mipmap.composite_src);
//        Drawable drawable = context.getResources().getDrawable(R.mipmap.composite_src);

//        sourceBitmapShader = new BitmapShader(sourceImage, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        destinationImage = BitmapFactory.decodeResource(context.getResources(), R.mipmap.composite_dst);

//        destinationbitmapShader = new BitmapShader(destinationImage, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

//        composeShader = new ComposeShader(destinationbitmapShader, sourceBitmapShader,
//                PorterDuff.Mode.DST_IN);
//        mPaint.setShader(composeShader);
//        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST);//只有目标图片
//        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
//        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);

        // 第一个 Shader：头像的 Bitmap
        /*Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        height = bitmap1.getHeight();
        width = bitmap1.getWidth();

        // 第二个 Shader：
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.batman_logo);
        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // ComposeShader：结合两个 Shader
        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_IN);
        mPaint.setShader(shader);*/

        /*bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.xong);
//        LightingColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x000000);
        ColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x003000);
        mPaint.setColorFilter(lightingColorFilter);*/

//        xDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//        xDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
//        xDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
//        xDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);

        bitmapZ = BitmapFactory.decodeResource(context.getResources(), R.mipmap.what_the_fuck);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "w: " + w + "\th: " + h + "\toldw: " + oldw + "\toldh: " + oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*mPaint.setColor(Color.parseColor("#009688"));
        canvas.drawRect(30, 30, 230, 180, mPaint);

        mPaint.setColor(Color.parseColor("#FF9800"));
        canvas.drawLine(300, 30, 450, 180, mPaint);

        mPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawText("HenCoder", 500, 130, mPaint);

        mPaint.setARGB(100, 255, 0, 0);
        canvas.drawRect(0, 0, 200, 200, mPaint);
        mPaint.setARGB(100, 0, 0, 0);
        canvas.drawLine(0, 0, 200, 200, mPaint);*/
//        mPaint.setShader(mLinearGradient);//在设置了 Shader 的情况下， Paint.setColor/ARGB() 所设置的颜色就不再起作用
//        canvas.drawCircle(300, 300, 200, mPaint);
//        mPaint.setShader(mRadialGradient);
//        canvas.drawCircle(300, 300, 200, mPaint);
//        mPaint.setShader(mSweepGradient);
//        canvas.drawCircle(300, 300, 200, mPaint);

//        mPaint.setShader(mBitmapShader);
//        canvas.drawCircle(300, 300, 200, mPaint);
        Log.e(TAG, "width: " + width + "\twidth: " + height);
//        canvas.drawCircle(300, 300, 300, mPaint);

//        canvas.drawBitmap(bitmap, 0, 0, mPaint);

        /*int saveLayer = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(sourceImage, 0, 0, mPaint);
        mPaint.setXfermode(xDuffXfermode);

        canvas.drawBitmap(destinationImage, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(saveLayer);*/

        //paint 设置style,三种
        /*mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(context.getResources().getColor(R.color.color1));
        canvas.drawCircle(300, 300, 200, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(600, 600, 200, mPaint);*/

        //设置线宽
        /*mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        canvas.drawCircle(150, 125, 100, mPaint);
        mPaint.setStrokeWidth(5);
        canvas.drawCircle(400, 125, 100, mPaint);
        mPaint.setStrokeWidth(40);
        canvas.drawCircle(650, 125, 100, mPaint);*/

       /* mPaint.setStrokeWidth(20);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100, 100, 500, 100, mPaint);

        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100, 200, 500, 200, mPaint);

        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100, 300, 500, 300, mPaint);*/

        /*mPaint.setShadowLayer(15, 5, 5, Color.RED);
        mPaint.setTextSize(64);
        mPaint.setColor(context.getResources().getColor(android.R.color.white));
        canvas.drawText("hello 哇！何瑶:$1000万", 200, 300, mPaint);*/

        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));

        canvas.drawBitmap(bitmapZ, 100, 100, mPaint);

        mPaint.setMaskFilter(null);

    }
}
