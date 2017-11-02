package com.yao.moduled;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yao.lib_mvp.base.BaseActivity;
import com.yao.resource.constants.RouterConstants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by heyao on 2017/10/25.
 */

@Route(path = RouterConstants.MODULE_D_ACTIVITY)
public class ModuleDActivity extends BaseActivity {

    @BindView(R2.id.module_d_tv)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_d_main_activity);
    }

    @OnClick({R2.id.module_d_tv})
    public void clickEvent(View view) {
        int id = view.getId();
        if (R.id.module_d_tv == id) {
            ARouter.getInstance().build(RouterConstants.MODULE_A_ACTIVITY).navigation();
        }
    }
}
