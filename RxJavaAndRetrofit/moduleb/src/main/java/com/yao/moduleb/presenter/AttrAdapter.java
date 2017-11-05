package com.yao.moduleb.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yao.moduleb.R;
import com.yao.moduleb.R2;
import com.yao.moduleb.model.entity.AttrValuesEntity;
import com.yao.moduleb.model.entity.AttrsEntity;
import com.yao.moduleb.model.entity.SkuAttrEntity;
import com.yao.moduleb.model.entity.SkuEntity;
import com.yao.moduleb.view.TagTextView;
import com.zhy.view.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/5 下午4:29
 * @Version:
 */

public class AttrAdapter extends RecyclerView.Adapter<AttrAdapter.AttrHolder> {

    private Context mContext;
    private final int SELECTED = 0x100;
    private final int CANCEL = 0x101;
    private List<AttrsEntity> attrsEntities;
    private SkuEntity defaultSkuEntity;
    //    private TagTextView[][] childrenViews;    //二维 装所有属性
    private SparseArray<List<TagTextView>> childrenViews;//装载所有属性

    public AttrAdapter(Context context) {
        this.mContext = context;
        attrsEntities = new ArrayList<>();
        childrenViews = new SparseArray<>();
    }

    @Override
    public AttrHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.module_b_layout_attr_tag, null);
        return new AttrHolder(view);
    }

    @Override
    public void onBindViewHolder(AttrHolder attrHolder, int i) {
        attrHolder.bindData(i);
    }

    @Override
    public int getItemCount() {
        return this.attrsEntities.size();
    }

    public void setAttrsEntities(List<AttrsEntity> attrsEntityList) {
        if (attrsEntityList == null || attrsEntityList.size() < 1) {
            throw new NullPointerException("attrsEntityList should not be null or empty");
        }
        this.attrsEntities.clear();
        this.attrsEntities.addAll(attrsEntityList);
        notifyItemChanged(attrsEntities.size());
    }

    public void setDefaultSku(SkuEntity defaultSku) {
        this.defaultSkuEntity = defaultSku;
    }

    class AttrHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.tv_attribute_name)
        TextView mTvName;

        @BindView(R2.id.flow_layout)
        FlowLayout mFlowLayout;

//        @BindView(R2.id.flex_layout)
//        FlexboxLayout mFlexBoxLayout;

        @BindDimen(R2.dimen.dp4)
        int dp4;

        @BindDimen(R2.dimen.dp12)
        int dp12;

        public AttrHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindData(int position) {
            AttrsEntity attrsEntity = attrsEntities.get(position);
            int attrKeyId = attrsEntity.getKeyId();//属性对应的key
            mTvName.setText(attrsEntity.getName());
            List<SkuAttrEntity> attrs = defaultSkuEntity.getAttrs();//默认sku对应的属性
            List<TagTextView> tagView = new ArrayList<>();//同一行的tagView

            List<AttrValuesEntity> attrValues = attrsEntity.getAttrValues();//当前属性存在的属性值比如color有：black、blue等
            int size = attrValues.size();
            for (int i = 0; i < size; i++) {
                AttrValuesEntity attrValuesEntity = attrValues.get(i);
                int attrValueId = attrValuesEntity.getValueId();//属性对应的value
                String value = attrValuesEntity.getValue();
                TagTextView tagTextView = (TagTextView) LayoutInflater.from(mContext).inflate(R.layout.module_b_layout_tag_item, null);
                tagTextView.setText(value);
                tagTextView.setPadding(dp12, dp4, dp12, dp4);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(dp4, dp4, dp4, dp4);
                mFlowLayout.addView(tagTextView, i, layoutParams);
                for (SkuAttrEntity skuAttrEntity : attrs) {
                    int keyId = skuAttrEntity.getKeyId();
                    if (attrKeyId != keyId) continue;
                    int valueId = skuAttrEntity.getValueId();
                    if (valueId == attrValueId) {
                        tagTextView.setEnabled(true);
                        tagTextView.setChecked(true);
                        break;
                    }
                }
                tagView.add(i, tagTextView);
            }
            childrenViews.put(position, tagView);
            initOptions();//初始化默认选中属性
        }
    }

    private void initOptions() {


    }

    class ClickListener implements View.OnClickListener {

        private int row;//行
        private int column;//列
        private int operator;//操作

        public ClickListener(int row, int column, int operator) {
            this.row = row;
            this.column = column;
            this.operator = operator;
        }

        @Override
        public void onClick(View v) {

        }
    }
}
