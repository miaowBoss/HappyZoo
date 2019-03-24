package com.taipei.happyZoo.custom.CustomPaginationListener;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager layoutManager;

    public PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((layoutManager.findLastCompletelyVisibleItemPosition() == -1
                    || layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.findLastVisibleItemPosition())
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
