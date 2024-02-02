package com.example.cinema.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import com.example.cinema.data.Film
import com.example.cinema.data.FilmResponse
import com.example.cinema.databinding.ListItemBinding

class AfficheAdapter : RecyclerView.Adapter<AfficheAdapter.Holder>() {

    private var films: List<Film> = listOf()

    fun setFilms(films: List<Film>) {
        this.films = films
        notifyDataSetChanged()
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)

        fun bind(film: Film) = with(binding) {
            title.text = film.name
            country.text = film.country.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount(): Int {
        return films.size
    }
}
