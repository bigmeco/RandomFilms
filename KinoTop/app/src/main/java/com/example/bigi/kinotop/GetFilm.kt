package com.example.bigi.kinotop;


import com.example.bigi.kinotop.data.NewFilm
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetFilm {
    @GET("movie/upcoming?page=1&language=ru-RU&api_key=ba8e8a114ce7fc27aa71ebec8c0b1afe")
    fun listNewFilm(): Observable<NewFilm>

    @GET("discover/movie?api_key=ba8e8a114ce7fc27aa71ebec8c0b1afe&language=ru-RU&sort_by=popularity.desc")
    fun randomFilm(@Query("page") pageList: Int): Observable<NewFilm>


    @GET("discover/movie?api_key=ba8e8a114ce7fc27aa71ebec8c0b1afe&language=ru-RU&sort_by=popularity.desc")
    fun randomGenresFilm(@Query("page") pageList: Int, @Query("with_genres") genreCount: Int): Observable<NewFilm>


}