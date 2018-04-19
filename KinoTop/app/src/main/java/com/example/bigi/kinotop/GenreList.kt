package com.example.bigi.kinotop

enum class GenreList(
        val names: String, val id: Int, val sizes: Int
) {
    ACTION("боевик", 28, 500),
    ADVENTURE("приключения", 12, 260),
    ANIMATION("мультфильм", 16, 220),
    COMEDY("комедия", 35, 840),
    CRIME("криминал", 80, 320),
    DOCUMENTARY("документальный", 99, 270),
    DRAMA("драма", 18, 850),
    FAMILY("семейный", 10751, 280),
    FANTASY("фэнтези", 14, 160),
    HISTORY("история", 36, 100),
    HORROR("ужасы", 27, 400),
    MUSIC("музыка", 10402, 210),
    MYSTERY("детектив", 9648, 180),
    ROMANCE("мелодрама", 10749, 490),
    FICTION("фантастика", 878, 220),
    TV("телевизионный фильм", 10770, 140),
    THRILLER("триллер", 53, 520),
    WAR("военный", 10752, 110),
    WESTERN("вестерн", 37, 90),


}
