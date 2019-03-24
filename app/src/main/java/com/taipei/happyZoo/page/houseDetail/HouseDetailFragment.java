package com.taipei.happyZoo.page.houseDetail;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.taipei.happyZoo.R;
import com.taipei.happyZoo.apiTool.HttpMethods;
import com.taipei.happyZoo.apiTool.model.HouseInfo;
import com.taipei.happyZoo.apiTool.model.PlantInfo;
import com.taipei.happyZoo.commonTool.ConvertTool;
import com.taipei.happyZoo.custom.CustomPaginationListener.PaginationScrollListener;
import com.taipei.happyZoo.custom.base.BaseFragment;
import com.taipei.happyZoo.page.MainActivity;
import com.taipei.happyZoo.page.PlantDetail.PlantDetailFragment;
import com.taipei.happyZoo.page.house.SpaceItemDecoration;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.observers.DisposableObserver;

public class HouseDetailFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivPic)
    ImageView ivPic;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.rvDetail)
    RecyclerView rvDetail;
    @BindView(R.id.fabLink)
    FloatingActionButton fabLink;
    private Unbinder unbinder;

    private static final String ARG_HOUSE_INFO = "houseInfo";
    private static final String ARG_HOUSE_ANIM_NAME = "animName";

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private boolean isFirstPage = true;

    private int mTotalCount = 0;
    private int mOffset = 0;

    private String mAnimName;
    private HouseInfo.ResultBean.ResultsBean mHouseInfo;
    private HouseDetailAdapter  mHouseDetailAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static HouseDetailFragment newInstance(HouseInfo.ResultBean.ResultsBean houseInfo , String animName) {
        HouseDetailFragment fragment = new HouseDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_HOUSE_INFO, houseInfo);
        args.putString(ARG_HOUSE_ANIM_NAME, animName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHouseInfo = (HouseInfo.ResultBean.ResultsBean) getArguments().getSerializable(ARG_HOUSE_INFO);
            mAnimName = getArguments().getString(ARG_HOUSE_ANIM_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_house_detail, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        ViewCompat.setTransitionName(ivPic,mAnimName);
        setDetailList();
        setHouseInfo();
        setToolbar();
        setListener();

        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setToolbar() {
        collapsingToolbar.setCollapsedTitleTypeface(Typeface.DEFAULT_BOLD);
        collapsingToolbar.setTitle(mHouseInfo.getE_Name());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_36dp);
    }

    private void setListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()==null) {
                    return;
                }
                getActivity().onBackPressed();
            }
        });

        rvDetail.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                getHouseInfoApi(mHouseInfo.getE_Name());
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        mHouseDetailAdapter.setItemOnclickListener(new HouseDetailAdapter.ItemOnclickListener() {
            @Override
            public void onClick(View view,PlantInfo.ResultBean.ResultsBean plantInfo) {
                if (getActivity() == null) {
                    return;
                }
                ((MainActivity) getActivity()).addFragment(PlantDetailFragment.newInstance(plantInfo));
            }
        });


        fabLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mHouseInfo.getE_URL()));
                startActivity(i);
            }
        });
    }

    private void setHouseInfo() {
        if (getContext() != null) {
            Glide.with(getContext())
                    .load(mHouseInfo.getE_Pic_URL())
                    .centerCrop()
                    .into(ivPic);
        }
        mHouseDetailAdapter.add(mHouseInfo);
    }

    private void setDetailList() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvDetail.setLayoutManager(linearLayoutManager);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(500);
        rvDetail.setItemAnimator(animator);

        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(ConvertTool.dp2px(getContext(), 2), ConvertTool.dp2px(getContext(), 4));
        rvDetail.addItemDecoration(spaceItemDecoration);

        mHouseDetailAdapter = new HouseDetailAdapter();
        rvDetail.setAdapter(mHouseDetailAdapter);


    }

    /**
     * Pagination
     */
    private void loadFirstPage(List<PlantInfo.ResultBean.ResultsBean> results) {
        isFirstPage = false;
        isLoading = false;
        mHouseDetailAdapter.addAll(results);

        if (mOffset <= mTotalCount) {
            mHouseDetailAdapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    private void loadNextPage(List<PlantInfo.ResultBean.ResultsBean> results) {
        mHouseDetailAdapter.removeLoadingFooter();
        isLoading = false;
        mHouseDetailAdapter.addAll(results);
        if (mOffset <= mTotalCount) {
            mHouseDetailAdapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    /**
     * call api
     */
    private void getHouseInfoApi(String search) {
        DisposableObserver<PlantInfo> subscriber = new DisposableObserver<PlantInfo>() {
            @Override
            public void onNext(PlantInfo plantInfo) {
                mTotalCount = plantInfo.getResult().getCount();
                mOffset = plantInfo.getResult().getOffset() + plantInfo.getResult().getLimit();


                if (isFirstPage) {
                    loadFirstPage(plantInfo.getResult().getResults());
                } else {
                    loadNextPage(plantInfo.getResult().getResults());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        HttpMethods.getInstance().getPlantInfoList(subscriber, search, 5, mOffset);
    }
}



