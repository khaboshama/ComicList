package com.khaled.comiclist.feature.favoriteList

import com.khaled.comiclist.common.BaseUseCase
import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.feature.comicList.module.data.ComicItem
import com.khaled.comiclist.feature.comicList.module.data.IComicRepository

class GetFavoriteComicsUseCase(repository: IComicRepository) : BaseUseCase<IComicRepository>(repository) {
    suspend operator fun invoke(pageNumber: Int, limit: Int): AppResult<List<ComicItem>> {
        return repository.getFavoriteComics(pageNumber, limit)
    }
}