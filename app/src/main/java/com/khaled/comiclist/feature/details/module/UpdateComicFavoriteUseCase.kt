package com.khaled.comiclist.feature.details.module

import com.khaled.comiclist.common.BaseUseCase
import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.feature.comicList.module.data.IComicRepository

class UpdateComicFavoriteUseCase(repository: IComicRepository) : BaseUseCase<IComicRepository>(repository) {
    suspend operator fun invoke(comicNumber: Int, isFavorite: Boolean): AppResult<Boolean> {
        return repository.updateComicFavorite(comicNumber, isFavorite)
    }
}