package com.yao.moduled;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yao.resource.constants.RouterConstants;

@Route(path = RouterConstants.MODULE_D_ACTIVITY)
public class ModuleDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_d_activity_main);
    }
}
