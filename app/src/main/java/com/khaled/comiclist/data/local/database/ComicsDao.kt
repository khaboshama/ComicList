package com.khaled.comiclist.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khaled.comiclist.feature.comicList.module.data.ComicItem

/**
 * Data Access Object for the comic table.
 */
@Dao
interface ComicsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg comicItem: ComicItem)

    @Query("SELECT * FROM ComicItem LIMIT :limit OFFSET :offset")
    suspend fun getComicsList(limit: Int, offset: Int): List<ComicItem>

    @Query("UPDATE ComicItem set is_favorite = :isFavorite where number = :comicNumber")
    suspend fun updateComicFavorite(comicNumber: Int, isFavorite: Boolean): Int

    @Query("SELECT * FROM ComicItem where is_favorite = 1 LIMIT :limit OFFSET :offset")
    suspend fun getFavoriteComicsList(limit: Int, offset: Int): List<ComicItem>

    @Query("SELECT DISTINCT * FROM ComicItem where number like :query or title like :query LIMIT :limit OFFSET :offset")
    suspend fun search(limit: Int, offset: Int, query: String): List<ComicItem>
}