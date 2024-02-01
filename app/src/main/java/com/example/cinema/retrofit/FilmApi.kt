package com.example.cinema.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface FilmApi {

    @GET("/cinema/film/{filmId}")
    suspend fun getFilmById(@Path("filmId") id: String): FilmResponse

    @GET("/cinema/today")
    suspend fun getFilmToday(): List<FilmResponse>

}