package com.maulikpract.api


import com.maulikpract.model.MovieData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/search/movie")
    suspend fun getMovieList(
        @Query("api_key") key: String = "55025e92d11983c6c3d1b4174da0e835",
        @Query("language") langauge: String = "en-US",
        @Query("query") query: String = "star",
        @Query("page") page: String = "1",
        @Query("include_adult") adult: String = "true"
    ): MovieData
}