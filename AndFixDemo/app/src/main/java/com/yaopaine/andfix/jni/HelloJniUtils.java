package com.yaopaine.andfix.jni;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 4/12/18
 */
public class HelloJniUtils {

    static {
        System.loadLibrary("hellonative");
    }

    public static native int add(int x, int y);

    public static native double div(double x, double y);

    public static native boolean isNative();

    public static native String getMessage();

    public static native void setup(int apilevel);
}
