package com.khaled.comiclist.di

import com.khaled.comiclist.common.ApplicationContext
import org.koin.java.KoinJavaComponent

object AppContext {
    val applicationContext by KoinJavaComponent.getKoin().inject<ApplicationContext>()
}