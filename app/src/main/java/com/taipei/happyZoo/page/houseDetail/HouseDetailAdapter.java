package com.taipei.happyZoo.page.houseDetail;

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
import com.taipei.happyZoo.apiTool.model.PlantInfo;
import com.taipei.happyZoo.custom.view.LoadingModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface ItemOnclickListener {
        void onClick(View view, PlantInfo.ResultBean.ResultsBean plantInfo);
    }

    public interface DetailType {
        int getType();
    }
    private static final int LOADING = 0;
    private static final int ITEM_PLANT = 1;
    private static final int ITEM_HOUSE_DETAIL = 2;

    private boolean isLoadingAdded = false;

    private ItemOnclickListener   mItemOnclickListener;
    private ArrayList<DetailType> mDetailInfo = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlantViewHolder plantViewHolder = new PlantViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false));
        HouseViewHolder houseViewHolder = new HouseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house_detail, parent, false));
        LoadingViewHolder loadingViewHolder = new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
        switch (viewType) {
            case LOADING:
                return loadingViewHolder;
            case ITEM_PLANT:
                return plantViewHolder;
            case ITEM_HOUSE_DETAIL:
                return houseViewHolder;
            default:
                return plantViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_PLANT) {
            PlantInfo.ResultBean.ResultsBean plantInfo = (PlantInfo.ResultBean.ResultsBean) mDetailInfo.get(position);
            PlantViewHolder plantViewHolder = (PlantViewHolder) holder;
            plantViewHolder.tvName.setText(plantInfo.getF_Name_Ch());
            plantViewHolder.tvKnow.setText(plantInfo.getF_AlsoKnown());
            Context context = plantViewHolder.ivPic.getContext();
            Glide.with(context)
                    .load(plantInfo.getF_Pic01_URL())
                    .centerCrop()
                    .into(plantViewHolder.ivPic);

        } else if (holder.getItemViewType() == ITEM_HOUSE_DETAIL) {
            HouseInfo.ResultBean.ResultsBean houseInfo = (HouseInfo.ResultBean.ResultsBean) mDetailInfo.get(position);
            HouseViewHolder houseViewHolder = (HouseViewHolder) holder;
            houseViewHolder.tvCategory.setText(houseInfo.getE_Category());
            houseViewHolder.tvContent.setText(houseInfo.getE_Info());
            houseViewHolder.tvMemo.setText(houseInfo.getE_Memo());
            houseViewHolder.tvName.setText(houseInfo.getE_Name());
        }
    }

    @Override
    public int getItemCount() {
        return mDetailInfo.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mDetailInfo.size() - 1 && isLoadingAdded) ? LOADING : mDetailInfo.get(position).getType();
    }

    public void setItemOnclickListener(ItemOnclickListener itemOnclickListener) {
        this.mItemOnclickListener = itemOnclickListener;
    }

    public <T extends DetailType> void add(T houseInfo) {
        mDetailInfo.add(houseInfo);
        notifyItemInserted(mDetailInfo.size() - 1);
    }

    public <T extends DetailType> void addAll(List<T> houseInfoList) {
        for (T detailType : houseInfoList) {
            add(detailType);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new LoadingModel());
    }

    public void removeLoadingFooter() {
        int position = mDetailInfo.size() - 1;
        int itemType = getItemViewType(position);
        isLoadingAdded = false;

        if (itemType == LOADING) {
            mDetailInfo.remove(position);
            notifyItemRemoved(position);
        }
    }

    class HouseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvMemo)
        TextView tvMemo;
        @BindView(R.id.tvCategory)
        TextView tvCategory;
        @BindView(R.id.tvName)
        TextView tvName;

        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    class PlantViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPic)
        ImageView ivPic;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvKnow)
        TextView tvKnow;
        @BindView(R.id.clItem)
        ConstraintLayout clItem;

        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            clItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemOnclickListener != null) {
                        mItemOnclickListener.onClick(itemView, (PlantInfo.ResultBean.ResultsBean) mDetailInfo.get(getLayoutPosition()));
                    }
                }
            });
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
}
