package com.khaled.comiclist.feature.comicList.screens

import androidx.lifecycle.MutableLiveData
import com.khaled.comiclist.common.BaseViewModel
import com.khaled.comiclist.common.data.AppResult
import com.khaled.comiclist.di.AppContext.applicationContext
import com.khaled.comiclist.feature.comicList.module.Mapper.toComicItemView
import com.khaled.comiclist.feature.comicList.module.useCase.GetComicsUseCase
import com.khaled.comiclist.feature.comicList.module.view.ComicItemView
import com.khaled.comiclist.utils.SingleLiveEvent

class ComicViewModel(
    private val getComicsUseCase: GetComicsUseCase,
) : BaseViewModel() {
    private var pageNumber = 1
    private val limit = 10
    private var isAllDataLoaded = false
    private var isComicsRequestFinished = true
    val showProgressBar = SingleLiveEvent<Boolean>()
    val comicList = MutableLiveData<MutableList<ComicItemView>>().apply {
    }

    fun getNextComics() {
        if (isComicsRequestFinished.not() || isAllDataLoaded) return
        isComicsRequestFinished = false
        if (comicList.value.isNullOrEmpty().not()) showProgressBar.value = true
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
                    if (comicList.value.isNullOrEmpty()) {
                        comicList.value = mutableListOf()
                    }
                    comicList.value?.addAll(it.data.map { comicItem -> comicItem.toComicItemView() })
                    isComicsRequestFinished = true
                    showProgressBar.value = false
                    pageNumber++
                },
                onError = {
                    error.value = getErrorMessage(it)
                    isComicsRequestFinished = true
                    showProgressBar.value = false
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