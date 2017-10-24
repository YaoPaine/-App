package com.yao.modulea;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.yao.lib_mvp.BaseActivity;

import butterknife.BindView;

public class ModuleAActivity extends BaseActivity {

    @BindView(R2.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_a);
        Log.e(TAG, "onCreate: " + tv);
        Intent intent = getIntent();
        String name = intent.getStringExtra("module");
        if (!TextUtils.isEmpty(name))
            tv.setText(name);
    }
}
