package com.khaled.comiclist.feature.comicList.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.khaled.comiclist.R
import com.khaled.comiclist.common.BaseAdapter
import com.khaled.comiclist.feature.comicList.module.view.ComicItemView

class ComicsAdapter(private val onItemClicked: (ComicItemView) -> Unit) :
    BaseAdapter<ComicItemView, ComicItemViewHolder>(diffCallback) {

    override fun getViewHolder(parent: ViewGroup, viewType: Int) = ComicItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_comic, parent, false), onItemClicked
    )

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ComicItemView>() {
            override fun areItemsTheSame(
                oldItem: ComicItemView,
                newItem: ComicItemView
            ): Boolean = oldItem.number == newItem.number

            override fun areContentsTheSame(
                oldItem: ComicItemView,
                newItem: ComicItemView
            ): Boolean = oldItem == newItem
        }
    }
}