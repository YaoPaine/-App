package com.yao.resource.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yao.resource.R;

/**
 * Created by yaopaine on 1/4/18.
 */

public class CustomView extends View {

    private static final int[] mAttr = {android.R.attr.text, R.attr.testAttr};
    private static final int ATTR_ANDROID_TEXT = 0;
    private static final int ATTR_TESTATTR = 1;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int attributeCount = attrs.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            Log.e("TAG", "attributeName: " + attributeName + "\tattributeValue: " + attributeValue);
        }

        /*TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.test);
        String text = typedArray.getString(R.styleable.test_android_text);
        int testAttr = typedArray.getColor(R.styleable.test_testAttr, -1);
        Log.e("TAG", "text: " + text + "\ttestAttr: " + testAttr);
        typedArray.recycle();*/

        TypedArray typedArray = context.obtainStyledAttributes(attrs, mAttr);
        String text = typedArray.getString(ATTR_ANDROID_TEXT);
        int testAttr = typedArray.getInteger(ATTR_TESTATTR, -1);
        Log.e("TAG", "text = " + text + " , textAttr = " + testAttr);
        typedArray.recycle();

    }
}
