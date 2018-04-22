package com.example.bigi.kinotop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import com.example.bigi.kinotop.data.NewFilmData
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_scrolling.*

class MeFullFilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me_full_film)
        nameFilmText.text = intent.getStringExtra("title")

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w500${intent.getStringExtra("posterPath")}")
                .placeholder(R.drawable.in_progress)
                .into(poster)
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w500${intent.getStringExtra("backdropPath")}")
                .placeholder(R.drawable.defaultes)
                .into(screanFilm)
        ratingView.setValueAnimated(intent.getFloatExtra("voteAverage",0.toFloat()), 1500)
        popularityView.setValueAnimated(intent.getFloatExtra("popularity",0.toFloat()), 1500)
        textDataView.text = intent.getStringExtra("releaseDate")
        textGNView.text = intent.getStringExtra("genreIds")
        textInfo.text = intent.getStringExtra("overview")
    }
}
