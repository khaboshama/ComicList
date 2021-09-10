package com.khaled.comiclist.feature.module.data

import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.common.data.IBaseRepository
import com.khaled.comiclist.feature.module.domain.Comic

interface IComicRepository : IBaseRepository {
    suspend fun getComics(number: Int, limit: Int): AppResult<List<Comic>>
}