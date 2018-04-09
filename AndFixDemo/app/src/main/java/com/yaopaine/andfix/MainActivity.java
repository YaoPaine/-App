package com.yaopaine.andfix;

import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private static final String FILE_END = ".apatch";//规定修复补丁的文件格式是.apatch文件
    private String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        //TextView tv = findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
        System.out.println(stringFromJNI());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mFilePath = Objects.requireNonNull(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)).getAbsolutePath() + "/pach/";
        }

        File file = new File(mFilePath);
        if (!file.exists()) {
            file.mkdir();
        }

    }

    public void onClick(View view) {
        boolean isShow = false;
        String str = "存在一个BUG";
        if (isShow) {
            str = "方法BUG修复完成";
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void onFix(View view) {
        BaseApp app = (BaseApp) getApplication();
        try {
            app.getPachManager().addPatch(mFilePath.concat("andfix").concat(FILE_END));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
