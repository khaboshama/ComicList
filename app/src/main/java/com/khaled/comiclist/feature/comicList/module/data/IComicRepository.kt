package com.khaled.comiclist.feature.comicList.module.data

import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.common.data.IBaseRepository

interface IComicRepository : IBaseRepository {
    suspend fun getComics(pageNumber: Int, limit: Int, lastItemId: Int?): AppResult<List<ComicItem>>
    suspend fun updateComicFavorite(comicNumber: Int, isFavorite: Boolean): AppResult<Boolean>
}