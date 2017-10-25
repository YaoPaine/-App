package com.yao.moduleb;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yao.lib_mvp.BaseActivity;
import com.yao.lib_mvp.R2;

import butterknife.OnClick;

public class ModuleBActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_b);

    }

    @OnClick({R2.id.tv_module_b})
    public void clickEvent(View view) {
        if (view.getId() == R.id.tv_module_b) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.yao.rxjavaandretrofit", "com.yao.modulec.ModuleCActivity"));
            startActivity(intent);
        }
    }
}
