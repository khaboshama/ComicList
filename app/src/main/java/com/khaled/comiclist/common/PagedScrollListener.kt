package com.khaled.comiclist.common

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PagedScrollListener(private val visibleThreshold: Int) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val lastVisibleItemPosition =
            (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
        val totalItemCount = recyclerView.layoutManager?.itemCount
        if (lastVisibleItemPosition + visibleThreshold >= totalItemCount!!) onLoadMore()
    }

    abstract fun onLoadMore()
}
