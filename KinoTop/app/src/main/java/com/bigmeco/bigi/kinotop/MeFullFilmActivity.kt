package com.bigmeco.bigi.kinotop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bigmeco.bigi.kinotop.model.MyFilm
import com.squareup.picasso.Picasso
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_scrolling.*

class MeFullFilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me_full_film)
        nameFilmText.text = intent.getStringExtra("title")
        val realm: Realm = Realm.getDefaultInstance()
        floatingLike.setOnClickListener {
            realm.executeTransaction { realm ->
                realm.where(MyFilm::class.java).equalTo("id", intent.getStringExtra("id")).findFirst()!!.deleteFromRealm()
            }
            floatingLike.setImageResource(R.drawable.heart_outline)
            floatingLike.isEnabled = false
        }

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w500${intent.getStringExtra("posterPath")}")
                .placeholder(R.drawable.in_progress)
                .into(poster)
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w500${intent.getStringExtra("backdropPath")}")
                .placeholder(R.drawable.defaultes)
                .into(screanFilm)
        ratingView.setValueAnimated(intent.getFloatExtra("voteAverage", 0.toFloat()), 1500)
        popularityView.setValueAnimated(intent.getFloatExtra("popularity", 0.toFloat()), 1500)
        textDataView.text = intent.getStringExtra("releaseDate")
        textGNView.text = intent.getStringExtra("genreIds")
        textInfo.text = intent.getStringExtra("overview")
    }
}
