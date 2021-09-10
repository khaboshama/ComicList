package com.khaled.comiclist.feature.module.data

import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.common.data.HttpUtils
import com.khaled.comiclist.data.remote.RetrofitClient
import com.khaled.comiclist.feature.module.domain.Comic

class ComicRepository : IComicRepository {

    override suspend fun getComics(number: Int, limit: Int): AppResult<List<Comic>> {
        val comicListAppResult = mutableListOf<Comic>()
        var errorAppResult: AppResult.Error? = null
        (1..limit).forEach {
            when (val result = HttpUtils.safeApiCall { RetrofitClient.comicApi.getComic(number) }) {
                is AppResult.Success -> comicListAppResult.add(result.data)
                is AppResult.Error -> {
                    errorAppResult = result
                    return AppResult.Error(
                        errorMessage = errorAppResult?.errorMessage,
                        errorMessageRes = errorAppResult?.errorMessageRes
                    )
                }
            }
        }
        return AppResult.Success(comicListAppResult)
    }
}