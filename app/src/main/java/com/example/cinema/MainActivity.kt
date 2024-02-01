package com.example.cinema

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.cinema.retrofit.FilmApi
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt = findViewById<TextView>(R.id.txt)
        val btn = findViewById<Button>(R.id.btn)

        fun provideOkHttpClientWithProgress(): OkHttpClient =
            OkHttpClient().newBuilder()
                .connectTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .build()


        val retrofit = Retrofit.Builder().
            client(provideOkHttpClientWithProgress()).
            baseUrl("https://shift-backend.onrender.com/").
            addConverterFactory(GsonConverterFactory.create()).build()

        val filmApi by lazy {
            retrofit.create(FilmApi::class.java)
        }

        btn.setOnClickListener {
            lifecycleScope.launch {
                lifecycleScope.launch {
                    try {
                        val filmsResponse = filmApi.getFilmById("5")
                        val film = filmsResponse.film
                        txt.text = film.name
                    } catch (e: Exception) {
                        Log.e("MainActivity", "Error fetching data: ${e.message}")
                        txt.text = "Ошибка при получении данных"
                    }
                }
            }
        }
    }
}