package com.bigmeco.bigi.kinotop

enum class GenreList(
        val names: String, val id: Int, val sizes: Int
) {
    ACTION("боевик", 28, 410),
    ADVENTURE("приключения", 12, 215),
    ANIMATION("мультфильм", 16, 182),
    COMEDY("комедия", 35,  695),
    CRIME("криминал", 80, 264),
    DOCUMENTARY("документальный", 99, 225),
    DRAMA("драма", 18, 703),
    FAMILY("семейный", 10751, 234),
    FANTASY("фэнтези", 14, 138),
    HISTORY("история", 36, 90),
    HORROR("ужасы", 27, 330),
    MUSIC("музыка", 10402,  170),
    MYSTERY("детектив", 9648, 162),
    ROMANCE("мелодрама", 10749, 414),
    FICTION("фантастика", 878, 180),
    TV("телевизионный фильм", 10770, 125),
    THRILLER("триллер", 53,  460),
    WAR("военный", 10752, 65),
    WESTERN("вестерн", 37, 27),


}
