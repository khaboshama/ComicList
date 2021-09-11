package com.khaled.comiclist.feature.favoriteList

import com.khaled.comiclist.common.BaseViewModel
import com.khaled.comiclist.feature.comicList.module.Mapper.toComicItemView
import com.khaled.comiclist.feature.comicList.module.view.ComicItemView
import com.khaled.comiclist.utils.SingleLiveEvent
import kotlinx.coroutines.Job

class FavoriteViewModel(
    private val getFavoriteComicsUseCase: GetFavoriteComicsUseCase,
) : BaseViewModel() {
    private var job: Job? = null
    private var pageNumber = 1
    private val limit = 10
    private var isAllDataLoaded = false
    private var isComicsResponseFinished = true
    val comicList = SingleLiveEvent<MutableList<ComicItemView>>().apply {
        value = mutableListOf()
    }

    fun getNextFavoriteComics(forceUpdate: Boolean = false) {
        if (forceUpdate) {
            pageNumber = 1
            isAllDataLoaded = false
            job?.cancel()
            comicList.value?.clear()
            isComicsResponseFinished = true
        }
        if (isComicsResponseFinished.not() || isAllDataLoaded) return
        isComicsResponseFinished = false
        getComics()
    }

    private fun getComics() {
        job = wrapBlockingOperation {
            handleResult(
                getFavoriteComicsUseCase(
                    pageNumber = pageNumber,
                    limit = limit,
                    lastItemId = comicList.value?.lastOrNull()?.number
                ),
                onSuccess = {
                    if (it.data.isEmpty()) isAllDataLoaded = true
                    comicList.value?.addAll(it.data.map { comicItem -> comicItem.toComicItemView() })
                    comicList.value = comicList.value
                    isComicsResponseFinished = true
                    pageNumber++
                },
                onError = {
                    error.value = getErrorMessage(it)!!
                    isComicsResponseFinished = true
                }
            )
        }
    }
}