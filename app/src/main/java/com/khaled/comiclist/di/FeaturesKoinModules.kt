package com.khaled.comiclist.di

import com.khaled.comiclist.MainViewModel
import com.khaled.comiclist.common.ApplicationContext
import com.khaled.comiclist.common.IApplicationContext
import com.khaled.comiclist.data.local.AppSharedPreference
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object FeaturesKoinModules {

    val allModules = ArrayList<Module>().apply {
        // application helpers module
        add(getAppHelperModule())
        // local data source module
        add(getDataSourceModule())
        // main screen
        add(getMainModule())
    }

    private fun getMainModule() = module { viewModel { MainViewModel() } }

    private fun getDataSourceModule() = module {
        single { AppSharedPreference(get()) }
    }

    private fun getAppHelperModule() = module {
        single<IApplicationContext> { ApplicationContext(androidContext()) }
    }
}