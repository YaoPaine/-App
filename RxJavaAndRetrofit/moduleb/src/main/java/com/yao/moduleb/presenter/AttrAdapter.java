package com.yao.moduleb.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
    private List<AttrsEntity> attrsEntities;
    private List<SkuEntity> mTotalSku;
    private String mDefaultSku;
    private TagTextView[][] mChildrenViews;
    private TagTextView[] mSelectView;//被选中的属性View

    public AttrAdapter(Context context) {
        this.mContext = context;
        attrsEntities = new ArrayList<>();
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
            TagTextView tagTextView = mSelectView[row];// 点击行，选中的view
            view.setChecked(!isChecked);
            int length = mSelectView.length;

            if (isChecked) {//判断当前点击的view是不是被选中的，如果是，则取消本次选中
                mSelectView[row] = null;
                List<SkuEntity> skuEntityList = new ArrayList<>();

                for (SkuEntity skuEntity : mTotalSku) {
                    int count = 0;//被取消选中的个数
                    int j = 0;
                    List<SkuAttrEntity> attrs = skuEntity.getAttrs();
                    for (SkuAttrEntity skuAttrEntity : attrs) {
                        int skuAttrEntityKeyId = skuAttrEntity.getKeyId();
                        int skuAttrEntityValueId = skuAttrEntity.getValueId();
                        for (TagTextView textView : mSelectView) {//找出当前选中view能够组成的sku
                            if (textView == null) {
                                count++;
                                continue;
                            }
                            int keyId = (int) textView.getTag(R.id.tag_text_view);
                            int valueId = (int) textView.getTag();
                            if ((keyId == skuAttrEntityKeyId) && (valueId == skuAttrEntityValueId)) {
                                j++;
                            }
                        }
                    }
                    if (j >= (length - count / length)) {
                        skuEntityList.add(skuEntity);
                    }
                }

                for (TagTextView[] tagTextViews : mChildrenViews) {
                    for (TagTextView tagView : tagTextViews) {
                        if (tagView.isChecked()) break;
                        tagView.setEnabled(false);
                        int keyId = (int) tagView.getTag(R.id.tag_text_view);
                        int valueId = (int) tagView.getTag();
                        for (SkuEntity skuEntity : skuEntityList) {
                            List<SkuAttrEntity> attrs = skuEntity.getAttrs();
                            for (SkuAttrEntity skuAttrEntity : attrs) {
                                int skuAttrEntityKeyId = skuAttrEntity.getKeyId();
                                int skuAttrEntityValueId = skuAttrEntity.getValueId();
                                if ((keyId == skuAttrEntityKeyId) && (valueId == skuAttrEntityValueId)) {
                                    tagView.setEnabled(true);
                                }
                            }
                        }
                    }
                }

                return;
            }
            //如果不是，则把当前view设置为checked，并取消之前被选中的view
            if (tagTextView != null) {
                tagTextView.setChecked(false);
            }
            mSelectView[row] = view;

            canClickOptions(row, column);
        }
    }

    /**
     * 当前选中的组合，是否存在sku，如果不存在则取消其它选中的
     */
    private void canClickOptions(int row, int column) {
        List<SkuEntity> skuEntities = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < mSelectView.length; i++) {
            TagTextView tagTextView = mSelectView[i];
            if (tagTextView == null) {
                j++;
                continue;
            }
            int keyId = (int) tagTextView.getTag(R.id.tag_text_view);
            int valueId = (int) tagTextView.getTag();
            for (SkuEntity skuEntity : mTotalSku) {
                List<SkuAttrEntity> attrs = skuEntity.getAttrs();
                SkuAttrEntity skuAttrEntity = attrs.get(i);
                int valueId1 = skuAttrEntity.getValueId();
                int keyId1 = skuAttrEntity.getKeyId();
                if (keyId != keyId1) continue;
                if (valueId != valueId1) continue;
                if (!skuEntities.contains(skuEntity))
                    skuEntities.add(skuEntity);
            }
        }


    }

    private void log(List<SkuEntity> skuEntityList) {
        if (skuEntityList == null) throw new NullPointerException("skuEntityList 为 null");
        for (SkuEntity skuEntity : skuEntityList) {
            Log.e("TAG", ": " + skuEntity.getAttrs());
        }
    }
}
