package com.khaled.comiclist.di

import com.khaled.comiclist.common.IApplicationContext
import org.koin.java.KoinJavaComponent

object AppContext {
    val applicationContext by KoinJavaComponent.getKoin().inject<IApplicationContext>()
}