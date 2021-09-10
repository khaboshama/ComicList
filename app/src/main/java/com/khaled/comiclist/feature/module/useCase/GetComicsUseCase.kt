package com.khaled.comiclist.feature.module.useCase

import com.khaled.comiclist.common.BaseUseCase
import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.feature.module.data.IComicRepository
import com.khaled.comiclist.feature.module.domain.Comic

class GetComicsUseCase(repository: IComicRepository) : BaseUseCase<IComicRepository>(repository) {
    suspend operator fun invoke(pageNumber: Int, limit: Int): AppResult<List<Comic>> {
        return repository.getComics(pageNumber, limit)
    }
}