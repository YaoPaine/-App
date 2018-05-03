package com.yao.hotfix;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yaopaine.andfix.R;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/3/18
 */
public class MainActivity_FIX extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @MethodReplace(className = "com.yao.hotfix.MainActivity", methodName = "onClick")
    public void onClick(View view) {
        showToast("bug已经被修复了");
        HotFixManager.getInstance().callOrigin(this, new Object[]{});
    }

    public void onFix(View view) {
        try {
            HotFixManager.getInstance().init(this);
            HotFixManager.getInstance().fix();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
