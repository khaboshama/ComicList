package com.khaled.comiclist.di

import androidx.room.Room
import com.khaled.comiclist.MainViewModel
import com.khaled.comiclist.common.ApplicationContext
import com.khaled.comiclist.common.IApplicationContext
import com.khaled.comiclist.data.local.AppSharedPreference
import com.khaled.comiclist.data.local.database.ComicsDatabase
import com.khaled.comiclist.feature.module.data.ComicRepository
import com.khaled.comiclist.feature.module.data.IComicRepository
import com.khaled.comiclist.feature.module.useCase.GetComicsUseCase
import com.khaled.comiclist.feature.screen.ComicViewModel
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
        // ComicList screen
        add(getComicListModule())
    }

    private fun getComicListModule() = module {
        factory<IComicRepository> { ComicRepository() }
        factory { GetComicsUseCase(get()) }
        viewModel { ComicViewModel(get()) }
    }

    private fun getMainModule() = module { viewModel { MainViewModel() } }

    private fun getDataSourceModule() = module {
        single { AppSharedPreference(get()) }
        single { Room.databaseBuilder(get(), ComicsDatabase::class.java, ComicsDatabase.DATABASE_NAME).build() }
        single { get<ComicsDatabase>().getComicsDao() }
    }

    private fun getAppHelperModule() = module {
        single<IApplicationContext> { ApplicationContext(androidContext()) }
    }
}