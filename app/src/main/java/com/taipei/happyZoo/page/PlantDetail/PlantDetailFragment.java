package com.taipei.happyZoo.page.PlantDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.taipei.happyZoo.R;
import com.taipei.happyZoo.apiTool.model.PlantInfo;
import com.taipei.happyZoo.custom.base.BaseFragment;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PlantDetailFragment extends BaseFragment {
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.ivPic)
    ImageView ivPic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder unbinder;

    private static final String ARG_PLANT_DETAIL = "plantDetail";

    private PlantInfo.ResultBean.ResultsBean mPlantDetail;

    public static PlantDetailFragment newInstance(PlantInfo.ResultBean.ResultsBean plantDetail) {
        PlantDetailFragment fragment = new PlantDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLANT_DETAIL, plantDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlantDetail = (PlantInfo.ResultBean.ResultsBean) getArguments().getSerializable(ARG_PLANT_DETAIL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_plant_detail, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        setToolBar();
        setPlantDetail();
        setListener();
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setToolBar() {
        toolbar.setTitle(mPlantDetail.getF_Name_Ch());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_36dp);
    }

    private void setListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() == null) {
                    return;
                }
                getActivity().onBackPressed();
            }
        });
    }

    private void setPlantDetail() {
        if (getContext() != null) {
            Glide.with(getContext())
                    .load(mPlantDetail.getF_Pic01_URL())
                    .centerCrop()
                    .into(ivPic);
        }
        toolbar.setTitle(mPlantDetail.getF_Name_Ch());
        llContent.addView(getBoardView(mPlantDetail.getF_Name_Ch(), mPlantDetail.getF_Name_En()));
        llContent.addView(getBoardView(getString(R.string.item_alsoKnown), mPlantDetail.getF_AlsoKnown()));
        llContent.addView(getBoardView(getString(R.string.item_brief), mPlantDetail.getF_Brief()));
        llContent.addView(getBoardView(getString(R.string.item_feature), mPlantDetail.getF_Feature()));
        llContent.addView(getBoardView(getString(R.string.item_function), mPlantDetail.get_$F_FunctionApplication141()));
        llContent.addView(getBoardView(getString(R.string.item_update), mPlantDetail.getF_Update()));

    }

    private View getBoardView(String title, String content) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_board, null, false);
        TextView tvTitle = inflate.findViewById(R.id.tvTitle);
        TextView tvContent = inflate.findViewById(R.id.tvContent);

        tvTitle.setText(title);
        tvContent.setText(content);
        return inflate;
    }
}
