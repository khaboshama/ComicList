package com.khaled.comiclist.feature.details

import androidx.lifecycle.MutableLiveData
import com.khaled.comiclist.common.BaseViewModel
import com.khaled.comiclist.feature.comicList.module.view.ComicItemView

class ComicDetailsViewModel : BaseViewModel() {
    val comicItem = MutableLiveData<ComicItemView>()
}