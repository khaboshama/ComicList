package com.khaled.comiclist.feature.module.data

import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.common.data.HttpUtils
import com.khaled.comiclist.data.local.database.ComicsDao
import com.khaled.comiclist.data.remote.RetrofitClient
import com.khaled.comiclist.feature.module.Mapper.toComicItem
import com.khaled.comiclist.feature.module.domain.Comic
import org.koin.java.KoinJavaComponent

class ComicRepository : IComicRepository {
    private val comicsDao by KoinJavaComponent.getKoin().inject<ComicsDao>()

    override suspend fun getComics(pageNumber: Int, limit: Int, lastItemId: Int?): AppResult<List<Comic>> {
        val comicListAppResult = mutableListOf<Comic>()
        val offset = (pageNumber - 1 * limit)
        val comicItemList = comicsDao.getComicsList(limit = 10, offset = offset)
        if (comicItemList.size < limit) return sendComicRequest(lastItemId, limit)
        return AppResult.Success(comicListAppResult)
    }

    private suspend fun sendComicRequest(lastItemId: Int?, limit: Int): AppResult<List<Comic>> {
        val comicListAppResult = mutableListOf<Comic>()
        val errorAppResult: AppResult.Error?
        for (index in FIRST_INDEX..limit) {
            val comicNumber = lastItemId?.plus(index) ?: index
            when (val result = HttpUtils.safeApiCall { RetrofitClient.comicApi.getComic(comicNumber) }) {
                is AppResult.Success -> comicListAppResult.add(result.data)
                is AppResult.Error -> {
                    errorAppResult = result
                    if (index == FIRST_INDEX) {
                        return getErrorAppResult(errorAppResult.errorMessage, errorAppResult.errorMessageRes)
                    }
                    break
                }
            }
        }
        comicsDao.insertAll(*comicListAppResult.map { comic -> comic.toComicItem() }.toTypedArray())
        return AppResult.Success(comicListAppResult)
    }

    private fun getErrorAppResult(errorMessage: String?, errorMessageRes: Int?) = AppResult.Error(
        errorMessage = errorMessage,
        errorMessageRes = errorMessageRes
    )

    companion object {
        private const val FIRST_INDEX = 1
    }
}