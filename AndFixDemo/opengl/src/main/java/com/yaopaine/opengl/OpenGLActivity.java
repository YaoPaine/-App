package com.yaopaine.opengl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/8/18
 */
public class OpenGLActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneGlSurfaceView glSurfaceView = new OneGlSurfaceView(this);
        setContentView(glSurfaceView);
    }
}
