package com.khaled.comiclist.feature.screen

import androidx.lifecycle.MutableLiveData
import com.khaled.comiclist.common.BaseViewModel
import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.di.AppContext.applicationContext
import com.khaled.comiclist.feature.module.Mapper.toComicItemView
import com.khaled.comiclist.feature.module.useCase.GetComicsUseCase
import com.khaled.comiclist.feature.module.view.ComicItemView

class ComicViewModel(
    private val getComicsUseCase: GetComicsUseCase,
) : BaseViewModel() {
    private var pageNumber = 1
    private val limit = 10
    private var isAllDataLoaded = false
    private var isComicsRequestFinished = true
    val comicList = MutableLiveData<MutableList<ComicItemView>>().apply {
        value = mutableListOf()
    }

    fun getNextComics() {
        if (isComicsRequestFinished.not()) return
        isComicsRequestFinished = true
        getComics()
    }

    private fun getComics() {
        wrapBlockingOperation {
            handleResult(
                getComicsUseCase.invoke(
                    pageNumber = pageNumber,
                    limit = limit,
                    lastItemId = comicList.value?.lastOrNull()?.number
                ),
                onSuccess = {
                    if (it.data.size < limit) isAllDataLoaded = true
                    comicList.value?.addAll(it.data.map { comic -> comic.toComicItemView() })
                    isComicsRequestFinished = true
                    pageNumber++
                },
                onError = {
                    error.value = getErrorMessage(it)
                    isComicsRequestFinished = true
                }
            )
        }
    }

    private fun getErrorMessage(error: AppResult.Error) = if (error.errorMessageRes != null) {
        applicationContext.context.getString(error.errorMessageRes)
    } else {
        error.errorMessage
    }
}