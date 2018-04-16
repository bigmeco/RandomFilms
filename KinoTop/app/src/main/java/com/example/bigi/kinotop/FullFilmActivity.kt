package com.example.bigi.kinotop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.bigi.kinotop.data.NewFilmData
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_scrolling.*

class FullFilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val filelist = intent.getStringExtra("FILM_INFO")
        val item: NewFilmData = Gson().fromJson(filelist, NewFilmData::class.java)

        nameFilmText.text = item.title
        ratingView.setValueAnimated(item.voteAverage!!, 1500)
        popularityView.setValueAnimated(item.popularity!!, 1500)
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w500${item.posterPath}")
                .placeholder(R.drawable.in_progress)
                .into(poster)
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/original${item.backdropPath}")
                .placeholder(R.drawable.defaultes)
                .into(screanFilm)
        textDataView.text = item.releaseDate
        textGenresView.text = continText(item.genreIds)
        textInfo.text = item.overview
    }
}
