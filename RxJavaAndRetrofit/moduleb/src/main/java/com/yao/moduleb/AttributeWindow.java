package com.yao.moduleb;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.yao.moduleb.model.entity.AttrsEntity;
import com.yao.moduleb.model.entity.GoodsEntity;
import com.yao.moduleb.model.entity.SkuEntity;
import com.yao.moduleb.presenter.AttrAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/3 下午5:07
 * @Version:
 */

public class AttributeWindow extends PopupWindow {

    @BindView(R2.id.ll_popup_window)
    LinearLayout llPopupWindow;

    @BindView(R2.id.window_goods_detail_img)
    ImageView ivGoods;

    @BindView(R2.id.rv_attribute)
    RecyclerView mRecyclerView;

    public AttributeWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.module_b_window_goods_property, null);
        ButterKnife.bind(this, view);
        setContentView(view);
        setOutsideTouchable(false);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹窗窗体的高度
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 在PopupWindow里面就加上下面两句代码，让键盘弹出时，不会挡住pop窗口。
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // 设置popupWindow以外可以触摸
        this.setOutsideTouchable(true);
        // 以下两个设置点击空白处时，隐藏掉pop窗口
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());

        // 设置动画--这里按需求设置成系统输入法动画
        this.setAnimationStyle(R.style.AnimBottom);
        // 添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = llPopupWindow.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP && y < height) {
                    dismiss();
                }
                return true;
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        AttrAdapter adapter = new AttrAdapter(context);
        mRecyclerView.setAdapter(adapter);
    }

    @OnClick({R2.id.iv_window_close})
    public void clickEvent(View view) {
        int id = view.getId();
        if (R.id.iv_window_close == id) {
            dismiss();
        }
    }

    /**
     * 初始化 属性dialog
     *
     * @param goodsEntity 商品详情数据
     * @param sku         默认显示的sku
     */
    public void initWindow(GoodsEntity goodsEntity, @Nullable String sku) {
        AttrAdapter adapter = (AttrAdapter) mRecyclerView.getAdapter();

        /**
         *查找出默认的sku
         */
        List<SkuEntity> skuEntityList = goodsEntity.getSkus();
        adapter.setTotalSku(skuEntityList);
        SkuEntity defaultSku = skuEntityList.get(0);
        if (!TextUtils.isEmpty(sku))
            for (SkuEntity skuEntity : skuEntityList) {
                if (TextUtils.equals(skuEntity.getSku(), sku)) {
                    defaultSku = skuEntity;
                    break;
                }
            }
        adapter.setDefaultSku(defaultSku.getSku());

        List<AttrsEntity> attrsEntityList = goodsEntity.getAttrs();//设置属性
        adapter.setAttrsEntities(attrsEntityList);

    }
}
