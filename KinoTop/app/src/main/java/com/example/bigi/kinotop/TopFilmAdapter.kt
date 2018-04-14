package com.example.bigi.kinotop

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bigi.kinotop.data.NewFilmData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_top_layout.view.*


class TopFilmAdapter(val items: List<NewFilmData>, val listener: (NewFilmData) -> Unit) : RecyclerView.Adapter<TopFilmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_top_layout, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: NewFilmData, listener: (NewFilmData) -> Unit) = with(itemView) {
            textName.text = item.title
            ratingView.setValue(item.voteAverage!!)
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w500${item.posterPath}")
                    .into(poster)
            textDataView.text = item.releaseDate
            textGenresView.text = continText(item.genreIds)
            setOnClickListener { listener(item) }
        }
    }
}

fun continText(item: List<Int?>?): String {
    var genres: String = ""
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