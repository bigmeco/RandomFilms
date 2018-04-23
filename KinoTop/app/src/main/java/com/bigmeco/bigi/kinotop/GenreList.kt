package com.bigmeco.bigi.kinotop

enum class GenreList(
        val names: String, val id: Int, val sizes: Int
) {
    ACTION("боевик", 28, 450),
    ADVENTURE("приключения", 12, 234),
    ANIMATION("мультфильм", 16, 198),
    COMEDY("комедия", 35, 756),
    CRIME("криминал", 80, 288),
    DOCUMENTARY("документальный", 99, 245),
    DRAMA("драма", 18, 765),
    FAMILY("семейный", 10751, 255),
    FANTASY("фэнтези", 14, 150),
    HISTORY("история", 36, 94),
    HORROR("ужасы", 27, 360),
    MUSIC("музыка", 10402, 186),
    MYSTERY("детектив", 9648, 175),
    ROMANCE("мелодрама", 10749, 450),
    FICTION("фантастика", 878, 200),
    TV("телевизионный фильм", 10770, 134),
    THRILLER("триллер", 53, 500),
    WAR("военный", 10752, 100),
    WESTERN("вестерн", 37, 86),


}
