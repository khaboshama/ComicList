package com.khaled.comiclist.feature.details

import androidx.lifecycle.MutableLiveData
import com.khaled.comiclist.common.BaseViewModel
import com.khaled.comiclist.feature.comicList.module.view.ComicItemView
import com.khaled.comiclist.feature.details.module.UpdateComicFavoriteUseCase

class ComicDetailsViewModel(
    private val updateComicFavoriteUseCase: UpdateComicFavoriteUseCase,
) : BaseViewModel() {
    private var isUpdateComicFinished = true
    val comicItemLiveData = MutableLiveData<ComicItemView>()
    fun onFavoriteClicked() {
        if (isUpdateComicFinished.not()) return
        isUpdateComicFinished = false
        wrapBlockingOperation {
            handleResult(
                updateComicFavoriteUseCase.invoke(
                    comicNumber = comicItemLiveData.value!!.number,
                    isFavorite = comicItemLiveData.value!!.isFavorite.not()
                ),
                onSuccess = {
                    if (it.data) comicItemLiveData.value?.isFavorite = comicItemLiveData.value!!.isFavorite.not()
                    comicItemLiveData.value = comicItemLiveData.value
                    isUpdateComicFinished = true
                }, onError = {
                    isUpdateComicFinished = true
                    error.value = getErrorMessage(it)!!
                }
            )
        }
    }

    fun setComicItem(comicItem: ComicItemView) {
        comicItemLiveData.value = comicItem
    }

}