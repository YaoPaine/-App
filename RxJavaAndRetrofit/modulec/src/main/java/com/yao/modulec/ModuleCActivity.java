package com.yao.modulec;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yao.lib_mvp.R2;
import com.yao.lib_mvp.base.BaseActivity;
import com.yao.lib_mvp.mvp2.model.entity.GoodsEntity;
import com.yao.lib_mvp.mvp2.persenter.SimplePresenter;
import com.yao.lib_mvp.mvp2.view.SimpleView;
import com.yao.modulec.component.DaggerSimpleComponent;
import com.yao.resource.constants.RouterConstants;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = RouterConstants.MODULE_C_ACTIVITY)
public class ModuleCActivity extends BaseActivity implements SimpleView {

    @BindView(R2.id.tv_module_c)
    TextView tv;

    @Named("initModel")
    @Inject
    SimplePresenter mSimplePresenter;

    @Named("initItself")
    @Inject
    SimplePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_c_activity_main);
        DaggerSimpleComponent.create().injectActivity(this);
        mSimplePresenter.attachView(this);

//        mSimplePresenter = new SimplePresenter();
    }

    @OnClick({R2.id.tv_module_c})
    public void clickEvent(View view) {
        int id = view.getId();
        if (R.id.tv_module_c == id) {
            /*Intent intent = new Intent();
            intent.setClassName("com.yao.rxjavaandretrofit", "com.yao.moduled.ModuleDActivity");
//            intent.setComponent(new ComponentName("com.yao.rxjavaandretrofit", "com.yao.moduled.ModuleDActivity"));
            startActivity(intent);*/

//            ARouter.getInstance().build(RouterConstants.MODULE_D_ACTIVITY).navigation();
            mSimplePresenter.clickRequest();
        }
    }

    @Override
    public void requestLoading() {
        tv.setText("请求数据中，请稍等...");
    }

    @Override
    public void resultSuccess(GoodsEntity result) {
        tv.setText(result.toString());
    }

    @Override
    public void resultFailure(String result) {
        tv.setText(result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSimplePresenter.detachView();
    }
}
