package com.khaled.comiclist.data.remote.endPoint

import com.khaled.comiclist.feature.comicList.module.domain.Comic
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicApi {

    @GET("{comicId}/info.0.json")
    suspend fun getComic(
        @Path("comicId") comicId: Int
    ): Response<Comic>
}