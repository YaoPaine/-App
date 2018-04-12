package com.yaopaine.andfix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yaopaine.andfix.jni.HelloJniUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    /*static {
        System.loadLibrary("native-lib");
    }*/

    private static final String FILE_END = ".apatch";//规定修复补丁的文件格式是.apatch文件
    private String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        //TextView tv = findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
        Log.e("TAG", "onCreate: " + HelloJniUtils.getMessage());
        Log.e("TAG", "onCreate: " + HelloJniUtils.add(1, 2));
        Log.e("TAG", "onCreate: " + HelloJniUtils.div(1, 2));
        Log.e("TAG", "onCreate: " + HelloJniUtils.isNative());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mFilePath = Objects.requireNonNull(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)).getAbsolutePath() + "/pach/";
        }

        File file = new File(mFilePath);
        if (!file.exists()) {
            file.mkdir();
        }

        Intent intent = new Intent("action");
        intent.setPackage(getPackageName());
        BroadcastReceiver receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                /*if ("action_received_data_from_visitor_bundle".equals(str)) {
                    if (com.jollycorp.jollychic.ui.account.notification.b.b(this.a, this.a.e(), intent)) {
                        com.jollycorp.jollychic.ui.other.func.c.c.a().e();
                        com.jollycorp.jollychic.ui.account.notification.b.a(this.a, this.a.e(), intent);
                        a.a(intent, this.a.j());
                        com.jollycorp.jollychic.ui.account.notification.b.a(intent);
                    }
                }*/
            }
        };
    }

    private static boolean b(Context context, Fragment fragment, Intent intent) {
        return context != null && fragment != null && intent != null && intent.getBundleExtra("key_received_data_from_visitor_bundle") != null;
    }

    public void onClick(View view) {
        boolean isShow = false;
        String str = "存在一个BUG";
        if (isShow) {
            str = "方法BUG修复完成";
        }
        //Toast.makeText(this, "原来你是我最想留住的幸运", Toast.LENGTH_SHORT).show();
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
}
