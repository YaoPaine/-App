package com.yao.moduleb.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yao.moduleb.R;
import com.yao.moduleb.R2;
import com.yao.moduleb.model.entity.AttrValuesEntity;
import com.yao.moduleb.model.entity.AttrsEntity;
import com.yao.moduleb.model.entity.SkuEntity;
import com.yao.moduleb.view.TagTextView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/4 上午10:36
 * @Version:
 */

public class AttributeAdapter extends RecyclerView.Adapter<AttributeAdapter.AttributeHolder> {
    private List<AttrsEntity> attrsEntities;
    private SkuEntity defaultSkuEntity;
    private Context mContext;

    public AttributeAdapter(Context context) {
        attrsEntities = new ArrayList<>();
        this.mContext = context;
    }

    @Override
    public AttributeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.module_b_layout_tag, null);
        return new AttributeHolder(view);
    }

    @Override
    public void onBindViewHolder(AttributeHolder attributeHolder, int i) {
        attributeHolder.bindData(i);
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

    class AttributeHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.flow_layout)
        TagFlowLayout mFlowLayout;

        @BindView(R2.id.tv_attr_name)
        TextView mTvName;

        public AttributeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(int position) {
            AttrsEntity attrsEntity = attrsEntities.get(position);
            mTvName.setText(attrsEntity.getName());
            List<AttrValuesEntity> attrValueEntities = attrsEntity.getAttrValues();
            mFlowLayout.setAdapter(new TagAdapter<AttrValuesEntity>(attrValueEntities) {

                @Override
                public View getView(FlowLayout flowLayout, int i, AttrValuesEntity attrValuesEntity) {
                    TagTextView view = (TagTextView) LayoutInflater.from(mContext).inflate(R.layout.module_b_layout_tag_item, flowLayout, false);
//                    Log.e("TAG", "getView: " + i);
                    if (i == 0) {
                        view.setEnabled(false);
                        view.setClickable(false);
                    } else {
                        view.setEnabled(true);
                    }
//                    Log.e("TAG", "" + (view.isClickable() + "\t" + view.isEnabled()));
                    view.setText(attrValuesEntity.getValue());
                    return view;
                }
            });
        }
    }
}
