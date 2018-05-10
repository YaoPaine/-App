package com.yaopaine.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.AttributeSet;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/8/18
 */
public class OneGlSurfaceView extends GLSurfaceView {

    public OneGlSurfaceView(Context context) {
        super(context);
        oneInit();
    }

    public OneGlSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        oneInit();
    }

    private void oneInit() {
        setEGLContextClientVersion(2);

        OneGlRender oneGlRender = new OneGlRender();
        setRenderer(oneGlRender);
    }

    public static class OneGlRender implements Renderer {

        private Triangle triangle;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            triangle = new Triangle();
        }

        private final float[] mMVPMatrix = new float[16];
        private final float[] mProjectionMatrix = new float[16];
        private final float[] mViewMatrix = new float[16];

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
            float ratio = (float) width / height;
            // 这个投影矩阵被应用于对象坐标在onDrawFrame（）方法中
            Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
//            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            // Set the camera position (View matrix)
            Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
            // Calculate the projection and view transformation
            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
            // Draw shape
            triangle.draw(mMVPMatrix);
        }

        public static int loadShader(int type, String shaderCode) {
            // 创造顶点着色器类型(GLES20.GL_VERTEX_SHADER)
            // 或者是片段着色器类型 (GLES20.GL_FRAGMENT_SHADER)
            int shader = GLES20.glCreateShader(type);
            // 添加上面编写的着色器代码并编译它
            GLES20.glShaderSource(shader, shaderCode);
            GLES20.glCompileShader(shader);
            return shader;
        }
    }
}
