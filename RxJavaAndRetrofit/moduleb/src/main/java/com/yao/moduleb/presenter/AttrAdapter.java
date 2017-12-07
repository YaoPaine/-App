package com.yao.moduleb.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.yao.moduleb.R;
import com.yao.moduleb.R2;
import com.yao.lib_mvp.mvp2.model.entity.AttrValuesEntity;
import com.yao.lib_mvp.mvp2.model.entity.AttrsEntity;
import com.yao.lib_mvp.mvp2.model.entity.SkuAttrEntity;
import com.yao.lib_mvp.mvp2.model.entity.SkuEntity;
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

    private final String TAG = "AttrAdapter";
    private Context mContext;
    private List<AttrsEntity> attrsEntities;
    private List<SkuEntity> mTotalSku;
    private String mDefaultSku;
    private TagTextView[][] mChildrenViews;
    private TagTextView[] mSelectView;//被选中的属性View
    private SparseIntArray mSelectAttr;

    public AttrAdapter(Context context) {
        this.mContext = context;
        attrsEntities = new ArrayList<>();
        mSelectAttr = new SparseIntArray();
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
        mSelectView = new TagTextView[attrsEntityList.size()];
        mChildrenViews = new TagTextView[attrsEntityList.size()][];
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
            List<AttrValuesEntity> attrValues = attrsEntity.getAttrValues();//当前属性存在的属性值比如color有：black、blue等

            int size = attrValues.size();
            TagTextView[] tagView = new TagTextView[size];

            for (int i = 0; i < size; i++) {
                AttrValuesEntity attrValuesEntity = attrValues.get(i);
                int attrValueId = attrValuesEntity.getValueId();//属性对应的value
                String value = attrValuesEntity.getValue();

                TagTextView tagTextView = (TagTextView) LayoutInflater.from(mContext).inflate(R.layout.module_b_layout_tag_item, null);
                tagTextView.setText(value);
                tagTextView.setEnabled(false);
                tagTextView.setTag(attrValueId);
                tagTextView.setTag(R.id.tag_text_view, attrKeyId);
                tagTextView.setTag(R.id.flex_layout, attrValueId);
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
                                mSelectView[position] = tagTextView;
                                mSelectAttr.put(keyId, valueId);
                            }
                        }
                    }
                }
                tagTextView.setOnClickListener(new ClickListener(position, i));
                tagView[i] = tagTextView;
            }
            mChildrenViews[position] = tagView;
        }
    }

    class ClickListener implements View.OnClickListener {

        private int row;//行
        private int column;//列

        public ClickListener(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void onClick(View v) {
            TagTextView view = (TagTextView) v;
            boolean isChecked = view.isChecked();
            view.setChecked(!isChecked);
            TagTextView tagTextView = mSelectView[row];// 点击行，选中的view
            int attrKeyId = -1;
            if (tagTextView != null) {//如果点击的这行之前有选中的属性，则删除这个选中的属性
                attrKeyId = (int) tagTextView.getTag(R.id.tag_text_view);
                mSelectAttr.delete(attrKeyId);
            }

            if (isChecked) {//取消操作
                //取消的是哪一行，
                mSelectView[row] = null;
                cancelOperator(attrKeyId);
            } else {//选中操作
                if (tagTextView != null) {
                    tagTextView.setChecked(false);
                }
                mSelectView[row] = view;
                int keyId = (int) view.getTag(R.id.tag_text_view);
                int valueId = (int) view.getTag(R.id.flex_layout);
                mSelectAttr.put(keyId, valueId);
                selectOperator(row, keyId);
            }


        }
    }

    /**
     * 每次点击，将非选中的view 的enable设置成false
     *
     * @param row 操作的那行
     */
    private void initOptions(int row) {
        for (int i = 0; i < mChildrenViews.length; i++) {
            if (i == row) continue;
            TagTextView[] childrenViews = mChildrenViews[i];
            for (TagTextView childView : childrenViews) {
                if (!childView.isChecked()) {
                    childView.setEnabled(false);
                }
            }
        }
    }

    /**
     * 取消操作
     *
     * @param attrKeyId 取消的那行的属性key
     */

    private void cancelOperator(int attrKeyId) {
        List<SkuEntity> canCheckSku = getEnableSku();

        //其实主要是取消行，有哪些需要变成enable，
        for (TagTextView[] childrenViews : mChildrenViews) {
            for (TagTextView childView : childrenViews) {
                int keyId = (int) childView.getTag(R.id.tag_text_view);
                int valueId = (int) childView.getTag(R.id.flex_layout);
                for (SkuEntity skuEntity : canCheckSku) {
                    List<SkuAttrEntity> attrs = skuEntity.getAttrs();
                    for (SkuAttrEntity skuAttrEntity : attrs) {
                        int skuAttrKeyId = skuAttrEntity.getKeyId();
                        int skuAttrValueId = skuAttrEntity.getValueId();
                        if ((skuAttrValueId == valueId) && (keyId == skuAttrKeyId)) {
                            childView.setEnabled(true);
                            break;
                        }
                    }

                    if (childView.isEnabled() || childView.isChecked()) break;
                }

            }
        }
    }

    /**
     * 选择操作
     *
     * @param row       操作的那行
     * @param attrKeyId 被选中的属性keyId
     */
    private void selectOperator(int row, int attrKeyId) {

        List<SkuEntity> canCheckSku = getEnableSku();
        //当选择了一个属性后，
        int length = mSelectView.length;
        if (canCheckSku.size() == 0) {//如果选择的属性组合，构不成sku，那么清掉其余行被选中的属性
            for (int i = 0; i < length; i++) {
                TagTextView tagTextView = mSelectView[i];
                if (tagTextView == null) continue;
                int keyId = (int) tagTextView.getTag(R.id.tag_text_view);
                int valueId = (int) tagTextView.getTag(R.id.flex_layout);
                if (keyId != attrKeyId) {
                    tagTextView.setChecked(false);
                    mSelectView[i] = null;
                    mSelectAttr.delete(keyId);
                }
            }

            initOptions(row);
            //还要将 包含当前选中属性的组合的sku，对应属性的view设置成可选
            List<SkuEntity> enableSku = getEnableSku();//获取包含选中属性的sku

            for (TagTextView[] childrenViews : mChildrenViews) {
                for (TagTextView childView : childrenViews) {
                    int keyId = (int) childView.getTag(R.id.tag_text_view);
                    int valueId = (int) childView.getTag(R.id.flex_layout);
                    for (SkuEntity skuEntity : enableSku) {
                        List<SkuAttrEntity> attrs = skuEntity.getAttrs();
                        for (SkuAttrEntity skuAttrEntity : attrs) {
                            int skuAttrKeyId = skuAttrEntity.getKeyId();
                            int skuAttrValueId = skuAttrEntity.getValueId();
                            if ((skuAttrValueId == valueId) && (keyId == skuAttrKeyId)) {
                                childView.setEnabled(true);
                                break;
                            }
                        }
                        if (childView.isEnabled() || childView.isChecked()) break;
                    }
                }
            }
        } else {
            //如果被选择的属性个数与属性本身具有的个数相同，则表示这个sku可用，那么不处理
            //如果被选择的属性少于属性本身具有的个数，那么遍历这个可选sku，将相应的属性设置成可选
            if (mSelectAttr.size() != length) {//如果选择的属性组合
                for (TagTextView[] childrenViews : mChildrenViews) {
                    for (TagTextView childView : childrenViews) {
                        int keyId = (int) childView.getTag(R.id.tag_text_view);
                        int valueId = (int) childView.getTag(R.id.flex_layout);
                        if (mSelectAttr.get(keyId, -1) == -1) {//当这个keyId不在被选属性中时
                            childView.setEnabled(false);
                        }
                        for (SkuEntity skuEntity : canCheckSku) {
                            List<SkuAttrEntity> attrs = skuEntity.getAttrs();
                            for (SkuAttrEntity skuAttrEntity : attrs) {
                                int skuAttrKeyId = skuAttrEntity.getKeyId();
                                int skuAttrValueId = skuAttrEntity.getValueId();
                                if ((skuAttrValueId == valueId) && (keyId == skuAttrKeyId))
                                    childView.setEnabled(true);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @return 获取当前选中的属性，能够有几个sku
     */
    private List<SkuEntity> getEnableSku() {
        List<SkuEntity> canCheckSku = new ArrayList<>();
        for (SkuEntity skuEntity : mTotalSku) {
            int count = 0;
            List<SkuAttrEntity> attrs = skuEntity.getAttrs();//一个sku下的全部属性
            for (SkuAttrEntity skuAttrEntity : attrs) {
                int keyId = skuAttrEntity.getKeyId();
                int valueId = skuAttrEntity.getValueId();
                if (mSelectAttr.get(keyId) == valueId) count++;
            }

            //一个sku的属性比对完毕之后
            //如果选中的属性，在sku中存在这样的组合，
            // 找出这样的sku，将相应的属性变成enable，被选中的属性变成checked，其余的属性变成unable
            if (count == mSelectAttr.size()) {
                canCheckSku.add(skuEntity);
            }
        }
        return canCheckSku;
    }

}
