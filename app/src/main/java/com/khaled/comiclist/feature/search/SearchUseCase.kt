package com.khaled.comiclist.feature.search

import com.khaled.comiclist.common.BaseUseCase
import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.feature.comicList.module.data.ComicItem
import com.khaled.comiclist.feature.comicList.module.data.IComicRepository

class SearchUseCase(repository: IComicRepository) : BaseUseCase<IComicRepository>(repository) {
    suspend operator fun invoke(pageNumber: Int, limit: Int, query: String): AppResult<List<ComicItem>> {
        return repository.search(pageNumber, limit, query)
    }
}