package com.example.bigi.kinotop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.bigi.kinotop.data.NewFilmData
import com.example.bigi.kinotop.model.MyFilm
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.list_top_layout.view.*

class FullFilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val filelist = intent.getStringExtra("FILM_INFO")
        val item: NewFilmData = Gson().fromJson(filelist, NewFilmData::class.java)
        val realm: Realm = Realm.getDefaultInstance()
        if (realm.where(MyFilm::class.java).equalTo("id", item.id.toString()).findAll().size == 0) {
            floatingLike.setImageResource(R.drawable.heart_outline)
            floatingLike.setOnClickListener {
                floatingLike.setImageResource(R.drawable.heart)
                val newLoveFilm = MyFilm()
                newLoveFilm.title = item.title
                newLoveFilm.voteCount = item.voteCount
                newLoveFilm.id = item.id.toString()
                newLoveFilm.video = item.video
                newLoveFilm.voteAverage = item.voteAverage
                newLoveFilm.popularity = item.popularity
                newLoveFilm.posterPath = item.posterPath
                newLoveFilm.originalLanguage = item.originalLanguage
                newLoveFilm.originalTitle = item.originalTitle
                newLoveFilm.genreIds = "Жанры: ${continText(item.genreIds)}"
                newLoveFilm.backdropPath = item.backdropPath
                newLoveFilm.adult = item.adult
                newLoveFilm.overview = item.overview
                newLoveFilm.releaseDate = item.releaseDate
                realm.executeTransaction { realm ->
                    realm.insert(newLoveFilm)
                }
            }
        } else {
            floatingLike.setImageResource(R.drawable.heart)
            floatingLike.setOnClickListener {
                realm.executeTransaction { realm ->
                    realm.where(MyFilm::class.java).equalTo("id", item.id.toString()).findFirst()!!.deleteFromRealm()
                }
                floatingLike.setImageResource(R.drawable.heart_outline)
            }
        }
        nameFilmText.text = item.title

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w500${item.posterPath}")
                .placeholder(R.drawable.in_progress)
                .into(poster)
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w500${item.backdropPath}")
                .placeholder(R.drawable.defaultes)
                .into(screanFilm)
        ratingView.setValueAnimated(item.voteAverage!!, 1500)
        popularityView.setValueAnimated(item.popularity!!, 1500)
        textDataView.text = item.releaseDate
        textGNView.text = continText(item.genreIds)
        textInfo.text = item.overview
    }
}
