package com.bigmeco.bigi.kinotop

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigmeco.bigi.kinotop.data.NewFilmData
import com.bigmeco.bigi.kinotop.model.MyFilm
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_top_layout.view.*
import com.google.gson.Gson
import io.realm.Realm


class TopFilmAdapter(val items: List<NewFilmData>, val listener: (NewFilmData) -> Unit) : RecyclerView.Adapter<TopFilmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_top_layout, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: NewFilmData, listener: (NewFilmData) -> Unit) = with(itemView) {
            val realm: Realm = Realm.getDefaultInstance()
            if (realm.where(MyFilm::class.java).equalTo("id", item.id.toString()).findAll().size == 0) {
                like.setImageResource(R.drawable.heart_outline)
                like.setOnClickListener {
                    like.setImageResource(R.drawable.heart)
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
                like.setImageResource(R.drawable.heart)
                like.setOnClickListener {
                    realm.executeTransaction { realm ->
                        realm.where(MyFilm::class.java).equalTo("id", item.id.toString()).findFirst()!!.deleteFromRealm()
                    }
                    like.setImageResource(R.drawable.heart_outline)
                }
            }
            internetButton.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://yandex.ru/search/?text=${item.title}")))

            }

            shareButton.setOnClickListener {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Рекомендую фильм '${item.title}' " +
                        "https://www.themoviedb.org/movie/${item.id}")
                sendIntent.type = "text/plain"
                context.startActivity(sendIntent)
            }

            textName.text = item.title
            ratingView.setValueAnimated(item.voteAverage!!, 1500)
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w400${item.posterPath}")
                    .placeholder(R.drawable.in_progress)
                    .into(poster)
            textDataView.text = item.releaseDate
            textGenresView.text = "Жанры:  ${continText(item.genreIds)}"
            setOnClickListener {
                Log.d("dd", item.toString())

                val intent = Intent(context, FullFilmActivity::class.java)
                val gson = Gson()
                gson.toJson(item)
                Log.d("dd", gson.toJson(item))
                intent.putExtra("FILM_INFO", gson.toJson(item))
                context.startActivity(intent)
            }
        }
    }
}

fun continText(item: List<Int?>?): String {
    var genres = ""
    if (item != null) {
        for (it in item) {
            for (id in GenreList.values()) {
                if (it == id.id) {
                    genres += "${id.names}, "

                }
            }
        }
    }
    return genres
}