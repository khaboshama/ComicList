package com.khaled.comiclist.feature.module.useCase

import com.khaled.comiclist.common.BaseUseCase
import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.feature.module.data.ComicItem
import com.khaled.comiclist.feature.module.data.IComicRepository

class GetComicsUseCase(repository: IComicRepository) : BaseUseCase<IComicRepository>(repository) {
    suspend operator fun invoke(pageNumber: Int, limit: Int, lastItemId: Int?): AppResult<List<ComicItem>> {
        return repository.getComics(pageNumber, limit, lastItemId)
    }
}