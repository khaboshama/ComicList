package com.khaled.comiclist.feature.screen

import android.view.View
import com.bumptech.glide.Glide
import com.khaled.comiclist.common.BaseAdapter
import com.khaled.comiclist.feature.module.view.ComicItemView
import kotlinx.android.synthetic.main.list_item_comic.view.*

class ComicItemViewHolder(private val view: View, private val onItemClicked: (ComicItemView) -> Unit) :
    BaseAdapter.BaseViewHolder<ComicItemView>(view) {

    override fun bindView(item: ComicItemView) {
        with(view) {
            numberTextView.text = item.number.toString()
            Glide.with(context).load(item.imageUrl).into(ic_ImageView)
            titleTextView.text = item.title
            setOnClickListener { onItemClicked(item) }
        }
    }
}