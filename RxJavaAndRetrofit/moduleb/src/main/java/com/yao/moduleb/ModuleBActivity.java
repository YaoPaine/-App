package com.yao.moduleb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yao.lib_common.model.entity.UserModel;
import com.yao.lib_mvp.base.BaseActivity;
import com.yao.resource.constants.RouterConstants;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = RouterConstants.MODULE_B_ACTIVITY)
public class ModuleBActivity extends BaseActivity {

    @BindView(R2.id.module_b_tv)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_b_main_activity);

    }

    @OnClick({R2.id.button_module_b})
    public void clickEvent(View view) {
        if (view.getId() == R.id.button_module_b) {
            /*Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.yao.rxjavaandretrofit", "com.yao.modulec.ModuleCActivity"));
            startActivity(intent);*/
            Intent intent = getIntent();
            if (null != intent) {
                UserModel user = (UserModel) intent.getExtras().getSerializable("user");
                if (user != null) {
                    textView.setText("name:" + user.getName() + "===password:" + user.getPassword());
                }
            }

        }
    }
}
