package com.taipei.happyZoo.page.house;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taipei.happyZoo.R;
import com.taipei.happyZoo.apiTool.HttpMethods;
import com.taipei.happyZoo.apiTool.model.HouseInfo;
import com.taipei.happyZoo.commonTool.ConvertTool;
import com.taipei.happyZoo.custom.CustomPaginationListener.PaginationScrollListener;
import com.taipei.happyZoo.custom.anim.DetailsTransition;
import com.taipei.happyZoo.custom.base.BaseFragment;
import com.taipei.happyZoo.page.MainActivity;
import com.taipei.happyZoo.page.houseDetail.HouseDetailFragment;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.ChangeImageTransform;
import androidx.transition.Fade;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.observers.DisposableObserver;

public class HouseFragment extends BaseFragment {

    @BindView(R.id.rvHouse)
    RecyclerView rvHouse;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder unbinder;

    private HouseAdapter mHouseAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private boolean isFirstPage = true;

    private int mTotalCount = 0;
    private int mOffset = 0;

    public static HouseFragment newInstance() {
        HouseFragment fragment = new HouseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_house, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        setToolbar();
        initHouseList();
        setListener();
        getHouseInfoApi();

        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setListener() {
        rvHouse.addOnScrollListener(new PaginationScrollListener(mLinearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                getHouseInfoApi();
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

        mHouseAdapter.setItemOnclickListener(new HouseAdapter.ItemOnclickListener() {
            @Override
            public void onClick(View view, HouseInfo.ResultBean.ResultsBean houseInfo) {
                if (getActivity() == null) {
                    return;
                }
                HouseFragment.this.setExitTransition(new Fade());

                ((MainActivity) getActivity()).replaceFragment(view, HouseDetailFragment.newInstance(houseInfo ,ViewCompat.getTransitionName(view)));
            }
        });
    }

    private void setToolbar() {
        toolbar.setTitle(getString(R.string.app_name));
    }

    private void initHouseList() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvHouse.setLayoutManager(mLinearLayoutManager);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(500);
        rvHouse.setItemAnimator(animator);

        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(ConvertTool.dp2px(getContext(), 2), ConvertTool.dp2px(getContext(), 4));
        rvHouse.addItemDecoration(spaceItemDecoration);

        if (mHouseAdapter == null) {
            mHouseAdapter = new HouseAdapter();
        }
        rvHouse.setAdapter(mHouseAdapter);
    }

    /**
     * Pagination
     */
    private void loadFirstPage(List<HouseInfo.ResultBean.ResultsBean> houseInfo) {
        isLoading = false;
        isFirstPage = false;
        mHouseAdapter.addAll(houseInfo);

        if (mOffset <= mTotalCount) {
            mHouseAdapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    private void loadNextPage(List<HouseInfo.ResultBean.ResultsBean> houseInfo) {
        mHouseAdapter.removeLoadingFooter();
        isLoading = false;
        mHouseAdapter.addAll(houseInfo);
        if (mOffset <= mTotalCount) {
            mHouseAdapter.addLoadingFooter();
        } else {
            isLastPage = true;
        }
    }

    /**
     * call api
     */
    private void getHouseInfoApi() {
        DisposableObserver<HouseInfo> subscriber = new DisposableObserver<HouseInfo>() {
            @Override
            public void onNext(HouseInfo houseInfo) {
                mTotalCount = houseInfo.getResult().getCount();
                mOffset = houseInfo.getResult().getOffset() + houseInfo.getResult().getLimit();

                if (isFirstPage) {
                    loadFirstPage(houseInfo.getResult().getResults());
                } else {
                    loadNextPage(houseInfo.getResult().getResults());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        HttpMethods.getInstance().getHouseIntroductionList(subscriber, 5, mOffset);
    }
}
