package com.yao.moduleb.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.yao.moduleb.R;
import com.yao.moduleb.R2;
import com.yao.moduleb.model.entity.AttrValuesEntity;
import com.yao.moduleb.model.entity.AttrsEntity;
import com.yao.moduleb.model.entity.SkuAttrEntity;
import com.yao.moduleb.model.entity.SkuEntity;
import com.yao.moduleb.view.TagTextView;

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
    private List<SkuEntity> mTotalSku;
    private String mDefaultSku;
    private SparseArray<List<TagTextView>> mChildrenViews;//装载所有属性
    private List<SparseArray<TagTextView>> mSelectTagView;//被选中的view

    public AttrAdapter(Context context) {
        this.mContext = context;
        attrsEntities = new ArrayList<>();
        mChildrenViews = new SparseArray<>();
        mSelectTagView = new ArrayList<>();
    }

    @Override
    public AttrHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.module_b_layout_attr_tag, viewGroup, false);
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

    public void setDefaultSku(String defaultSku) {
        this.mDefaultSku = defaultSku;
    }

    public void setTotalSku(List<SkuEntity> totalSku) {
        this.mTotalSku = totalSku;
    }

    class AttrHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.tv_attribute_name)
        TextView mTvName;

        @BindView(R2.id.flex_layout)
        FlexboxLayout mFlexBoxLayout;

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

            List<TagTextView> tagView = new ArrayList<>();//同一行的tagView
            List<AttrValuesEntity> attrValues = attrsEntity.getAttrValues();//当前属性存在的属性值比如color有：black、blue等

            int size = attrValues.size();
            for (int i = 0; i < size; i++) {
                AttrValuesEntity attrValuesEntity = attrValues.get(i);
                int attrValueId = attrValuesEntity.getValueId();//属性对应的value
                String value = attrValuesEntity.getValue();

                TagTextView tagTextView = (TagTextView) LayoutInflater.from(mContext).inflate(R.layout.module_b_layout_tag_item, null);
                tagTextView.setText(value);
                tagTextView.setEnabled(false);
                FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT
                        , FlexboxLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(dp4, dp4, dp4, dp4);
                mFlexBoxLayout.addView(tagTextView, layoutParams);

                for (SkuEntity skuEntity : mTotalSku) {
                    List<SkuAttrEntity> skuAttrEntities = skuEntity.getAttrs();
                    for (SkuAttrEntity skuAttrEntity : skuAttrEntities) {
                        int keyId = skuAttrEntity.getKeyId();
                        if (keyId != attrKeyId) continue;
                        int valueId = skuAttrEntity.getValueId();
                        if (valueId == attrValueId) {
                            tagTextView.setEnabled(true);
                            String sku = skuAttrEntity.getSku();
                            if (TextUtils.equals(sku, mDefaultSku)) {
                                tagTextView.setChecked(true);
                                SparseArray<TagTextView> sparseArray = new SparseArray<>();
                                sparseArray.put(i, tagTextView);
                                mSelectTagView.add(sparseArray);
                            }
                        }
                    }
                }

                boolean checked = tagTextView.isChecked();
                tagTextView.setOnClickListener(new ClickListener(position, i, checked ? SELECTED : CANCEL));
                tagView.add(i, tagTextView);
            }
            mChildrenViews.put(position, tagView);
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
            TagTextView view = (TagTextView) v;
            boolean isChecked = view.isChecked();
            SparseArray<TagTextView> sparseArray = mSelectTagView.get(row);//点击行，选中的view
            view.setChecked(!isChecked);
            if (isChecked) {//判断当前点击的view是不是被选中的，如果是，则取消本次选中
                sparseArray.delete(column);
            } else {//如果不是，则把当前view设置为checked，并取消之前被选中的view
                if (sparseArray.valueAt(0) != null && sparseArray.valueAt(0) instanceof TagTextView) {//sparseArray清空以后获取的对象为object，而不是null
                    TagTextView tagTextView = sparseArray.valueAt(0);
                    tagTextView.setChecked(false);
                    sparseArray.clear();
                }
                sparseArray.put(column, view);
            }


        }
    }
}
