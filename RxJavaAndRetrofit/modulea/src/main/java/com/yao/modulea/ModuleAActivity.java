package com.yao.modulea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yao.lib_mvp.BaseActivity;
import com.yao.resource.constants.RouterConstants;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = RouterConstants.MODULE_A_ACTIVITY)
public class ModuleAActivity extends BaseActivity {

    @BindView(R2.id.tv)
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_a);
        Intent intent = getIntent();
        String name = intent.getStringExtra("module");
        if (!TextUtils.isEmpty(name))
            tv.setText(name);
    }

    @OnClick({R2.id.tv, R2.id.button_get_fragment})
    public void clickEvent(View view) {
        int id = view.getId();
        if (id == R.id.tv) {
            /*Intent intent = new Intent();
            intent.setAction("com.yao.module.b.main.activity");
            startActivity(intent);*/
            Bundle bundle = getIntent().getExtras();
            ARouter.getInstance().build(RouterConstants.MODULE_B_ACTIVITY).with(bundle).navigation();
        } else if (id == R.id.button_get_fragment) {
            Fragment fragment = (Fragment) ARouter.getInstance().build(RouterConstants.MODULE_B_FRAGMENT).navigation();
            if (null != fragment) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.module_a_container, fragment).commit();
            }
        }
    }
}
