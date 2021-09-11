package com.khaled.comiclist.feature.comicList.module

import com.khaled.comiclist.feature.comicList.module.data.ComicItem
import com.khaled.comiclist.feature.comicList.module.domain.Comic
import com.khaled.comiclist.feature.comicList.module.view.ComicItemView

object Mapper {

    fun Comic.toComicItem() = ComicItem(
        number = number,
        title = title,
        description = description,
        imageUrl = imageUrl
    )

    fun ComicItem.toComicItemView() = ComicItemView(
        number = number,
        title = title,
        description = description,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )
}