package com.example.cinema

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.data.FilmRepository
import com.example.cinema.databinding.ActivityMainBinding
import com.example.cinema.ui.AfficheAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: AfficheAdapter
    lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AfficheAdapter()
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = adapter
        val repository = FilmRepository()

        lifecycleScope.launch {
            try {
                val films = repository.getFilmToday()
                Log.d("FilmList", "FilmResponse: $films")
                adapter.setFilms(films.films)
            } catch (e: Exception) {
                Log.e("FilmList", "Error: ${e.message}", e)
            }
        }
    }
}