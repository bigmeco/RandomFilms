package com.bigmeco.bigi.kinotop.model;

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class MyFilm : RealmObject() {

    var title: String? = null
    var voteCount: Int? = null
    @PrimaryKey
    var id: String? = null
    var video: Boolean? = null
    var voteAverage: Float? = null
    var popularity: Float? = null
    var posterPath: String? = null
    var originalLanguage: String? = null
    var originalTitle: String? = null
    var genreIds: String? = null
    var backdropPath: String? = null
    var adult: Boolean? = null
    var overview: String? = null
    var releaseDate: String? = null


}