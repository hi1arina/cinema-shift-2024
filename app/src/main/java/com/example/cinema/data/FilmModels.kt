package com.example.cinema.data

data class FilmResponse(
    val success: Boolean,
    val reason: String,
    val film: Film
)

data class Film(
    val id: String,
    val name: String,
    val originalName: String,
    val description: String,
    val releaseDate: String,
    val actors: List<Actor>,
    val directors: List<Director>,
    val runtime: Number,
    val ageRating: AgeRating,
    val genres: List<String>,
    val userRatings: UserRating,
    val img: String,
    val country: Country
)

data class Actor(
    val id: String,
    val professions: List<String>,
    val fullName: String
)

data class Director(
    val id: String,
    val professions: List<String>,
    val fullName: String
)

enum class AgeRating{
    G,
    PG,
    PG13,
    R,
    NC17;
}

enum class Professions {
    ACTOR,
    DIRECTOR;
}

data class UserRating(
    val kinopoisk: String,
    val imdb: String
)

data class Country(
    val name: String,
    val code: String,
    val code2: String,
    val id: Number
)