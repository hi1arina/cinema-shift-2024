package com.example.cinema.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FilmRepository {

    private companion object {
        const val BASE_URL = "https://shift-backend.onrender.com/"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
    }

    private fun provideOkHttpClientWithProgress(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

    private val retrofit = Retrofit.Builder()
        .client(provideOkHttpClientWithProgress())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val filmApi by lazy {
        retrofit.create(FilmApi::class.java)
    }

    suspend fun getFilmToday(): FilmResponse =
        filmApi.getFilmToday()

    suspend fun getFilmById(id: String): FilmResponse =
        filmApi.getFilmById(id)

}