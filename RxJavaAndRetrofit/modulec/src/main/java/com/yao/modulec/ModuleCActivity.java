package com.yao.modulec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yao.lib_mvp.BaseActivity;
import com.yao.lib_mvp.R2;

import butterknife.OnClick;

public class ModuleCActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_c_activity_main);
    }

    @OnClick({R2.id.tv_module_c})
    public void clickEvent(View view) {
        int id = view.getId();
        if (R.id.tv_module_c == id) {
            Intent intent = new Intent();
            intent.setClassName("com.yao.rxjavaandretrofit", "com.yao.moduled.ModuleDActivity");
//            intent.setComponent(new ComponentName("com.yao.rxjavaandretrofit", "com.yao.moduled.ModuleDActivity"));
            startActivity(intent);
        }
    }
}
