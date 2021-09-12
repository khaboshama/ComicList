package com.khaled.comiclist.feature.search

import com.khaled.comiclist.common.BaseViewModel
import com.khaled.comiclist.feature.comicList.module.Mapper.toComicItemView
import com.khaled.comiclist.feature.comicList.module.view.ComicItemView
import com.khaled.comiclist.utils.SingleLiveEvent
import kotlinx.coroutines.Job

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
) : BaseViewModel() {
    private var job: Job? = null
    private var pageNumber = 1
    private var currentQuery = ""
    private val limit = 10
    private var isAllDataLoaded = false
    private var isComicsResponseFinished = true
    val comicList = SingleLiveEvent<MutableList<ComicItemView>>().apply {
        value = mutableListOf()
    }

    fun getNextComics() {
        if (isComicsResponseFinished.not() || isAllDataLoaded) return
        isComicsResponseFinished = false
        getComics()
    }

    private fun getComics() {
        wrapBlockingOperation {
            handleResult(
                searchUseCase.invoke(
                    pageNumber = pageNumber,
                    limit = limit,
                    query = currentQuery
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

    fun onSearchChanged(query: String) {
        val queryFormatted = query.trim()
        if (queryFormatted.isEmpty()) return
        clearData()
        if (isComicsResponseFinished.not() || isAllDataLoaded) return
        isComicsResponseFinished = false
        currentQuery = queryFormatted
        getComics()
    }

    private fun clearData() {
        pageNumber = 1
        isAllDataLoaded = false
        job?.cancel()
        comicList.value?.clear()
        isComicsResponseFinished = true
    }
}