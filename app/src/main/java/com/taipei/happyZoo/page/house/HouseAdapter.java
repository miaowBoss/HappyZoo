package com.taipei.happyZoo.page.house;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.SpinKitView;
import com.taipei.happyZoo.R;
import com.taipei.happyZoo.apiTool.model.HouseInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface ItemOnclickListener {
        void onClick(View view, HouseInfo.ResultBean.ResultsBean houseInfo);
    }

    private static final int LOADING = 0;
    private static final int ITEM = 1;

    private boolean isLoadingAdded = false;
    private ItemOnclickListener mItemOnclickListener;
    private ArrayList<HouseInfo.ResultBean.ResultsBean> mHouseList = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_house, parent, false));
        LoadingViewHolder loadingViewHolder = new LoadingViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loading, parent, false));

        return viewType == ITEM ? viewHolder : loadingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM) {
            ViewHolder viewHolder = (ViewHolder) holder;
            HouseInfo.ResultBean.ResultsBean houseInfo = mHouseList.get(position);
            viewHolder.tvTitle.setText(houseInfo.getE_Name());
            viewHolder.tvContent.setText(houseInfo.getE_Info());
            viewHolder.tvCategory.setText(houseInfo.getE_Category());

            Context context = viewHolder.ivPic.getContext();
            Glide.with(context)
                    .load(houseInfo.getE_Pic_URL())
                    .centerCrop()
                    .into(viewHolder.ivPic);
        }
    }

    @Override
    public int getItemCount() {
        return mHouseList == null ? 0 : mHouseList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mHouseList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void setItemOnclickListener(ItemOnclickListener itemOnclickListener) {
        this.mItemOnclickListener = itemOnclickListener;
    }

    public void add(HouseInfo.ResultBean.ResultsBean houseInfo) {
        mHouseList.add(houseInfo);
        notifyItemInserted(mHouseList.size() - 1);
    }

    public void addAll(List<HouseInfo.ResultBean.ResultsBean> houseInfoList) {
        for (HouseInfo.ResultBean.ResultsBean houseInfo : houseInfoList) {
            add(houseInfo);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new HouseInfo.ResultBean.ResultsBean());
    }

    public void removeLoadingFooter() {
        int position = mHouseList.size() - 1;
        int itemType = getItemViewType(position);
        isLoadingAdded = false;

        if (itemType == LOADING) {
            mHouseList.remove(position);
            notifyItemRemoved(position);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.spinKit)
        SpinKitView spinKitView;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            spinKitView.setColor(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimaryDark));
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPic)
        ImageView ivPic;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvCategory)
        TextView tvCategory;
        @BindView(R.id.vMask)
        View vMask;
        @BindView(R.id.clItem)
        ConstraintLayout clItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            clItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewCompat.setTransitionName(ivPic, ivPic.getContext().getString(R.string.anim_key) + getLayoutPosition());

                    if (mItemOnclickListener != null) {
                        mItemOnclickListener.onClick(ivPic, mHouseList.get(getLayoutPosition()));
                    }
                }
            });

        }
    }
}
