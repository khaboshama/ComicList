package com.khaled.comiclist.feature.module

import com.khaled.comiclist.feature.module.data.ComicItem
import com.khaled.comiclist.feature.module.domain.Comic
import com.khaled.comiclist.feature.module.view.ComicItemView

object Mapper {

    fun Comic.toComicItemView() = ComicItemView(
        number = number,
        title = title,
        description = description,
        imageUrl = imageUrl
    )

    fun Comic.toComicItem() = ComicItem(
        number = number,
        title = title,
        description = description,
        imageUrl = imageUrl
    )
}