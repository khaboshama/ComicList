package com.khaled.comiclist.di

import androidx.room.Room
import com.khaled.comiclist.MainViewModel
import com.khaled.comiclist.common.ApplicationContext
import com.khaled.comiclist.common.IApplicationContext
import com.khaled.comiclist.data.local.AppSharedPreference
import com.khaled.comiclist.data.local.database.ComicsDatabase
import com.khaled.comiclist.feature.comicList.module.data.ComicRepository
import com.khaled.comiclist.feature.comicList.module.data.IComicRepository
import com.khaled.comiclist.feature.comicList.module.useCase.GetComicsUseCase
import com.khaled.comiclist.feature.comicList.screens.ComicViewModel
import com.khaled.comiclist.feature.details.ComicDetailsViewModel
import com.khaled.comiclist.feature.details.module.UpdateComicFavoriteUseCase
import com.khaled.comiclist.feature.favoriteList.FavoriteViewModel
import com.khaled.comiclist.feature.favoriteList.GetFavoriteComicsUseCase
import com.khaled.comiclist.feature.search.SearchUseCase
import com.khaled.comiclist.feature.search.SearchViewModel
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
        // Comic details screen
        add(getComicDetailsModule())
        // Favorite Comic screen
        add(getFavoriteComicModule())
        // Search Comic screen
        add(getSearchModule())
    }

    private fun getComicListModule() = module {
        factory<IComicRepository> { ComicRepository() }
        factory { GetComicsUseCase(get()) }
        viewModel { ComicViewModel(get()) }
    }

    private fun getComicDetailsModule() = module {
        factory { UpdateComicFavoriteUseCase(get()) }
        viewModel { ComicDetailsViewModel(get()) }
    }

    private fun getFavoriteComicModule() = module {
        factory { GetFavoriteComicsUseCase(get()) }
        viewModel { FavoriteViewModel(get()) }
    }

    private fun getSearchModule() = module {
        factory { SearchUseCase(get()) }
        viewModel { SearchViewModel(get()) }
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