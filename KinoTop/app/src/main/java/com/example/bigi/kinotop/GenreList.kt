package com.example.bigi.kinotop

enum class GenreList(
        val names: String, val id: Int, val sizes: Int
) {
    ACTION("боевик", 28, 999),
    ADVENTURE("приключения", 12, 565),
    ANIMATION("мультфильм", 16, 745),
    COMEDY("комедия", 35, 999),
    CRIME("криминал", 80, 745),
    DOCUMENTARY("документальный", 99, 999),
    DRAMA("драма", 18, 999),
    FAMILY("семейный", 10751, 590),
    FANTASY("фэнтези", 14, 386),
    HISTORY("история", 36, 265),
    HORROR("ужасы", 27, 925),
    MUSIC("музыка", 10402, 715),
    MYSTERY("детектив", 9648, 372),
    ROMANCE("мелодрама", 10749, 999),
    FICTION("фантастика", 878, 442),
    TV("телевизионный фильм", 10770, 250),
    THRILLER("триллер", 53, 999),
    WAR("военный", 10752, 230),
    WESTERN("вестерн", 37, 236),


}
