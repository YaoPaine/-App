package com.yao.rxjavaandretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yao.lib_mvp.BaseActivity;
import com.yao.lib_mvp.R2;
import com.yao.modulea.ModuleAActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick({R2.id.button1, R2.id.button2, R2.id.button3})
    public void clickEvent(View view) {
        Log.e(TAG, "clickEvent: ");
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.button1:
                intent.setClass(this, ModuleAActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent.setClass(this, ModuleAActivity.class);
                intent.putExtra("module", "my name is he yao");
                startActivity(intent);
                break;
            case R.id.button3:

                break;
        }
    }
}
