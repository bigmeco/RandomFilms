package com.example.bigi.kinotop

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bigi.kinotop.data.NewFilmData
import com.example.bigi.kinotop.model.MyFilm
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_top_layout.view.*
import com.google.gson.Gson


class MyFilmAdapter(val items: List<MyFilm>) : RecyclerView.Adapter<MyFilmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_top_layout, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MyFilm) = with(itemView) {
            textName.text = item.title
            like.setImageResource(R.drawable.heart)
            ratingView.setValueAnimated(item.voteAverage!!,1500)
            //ratingView.setValue(item.voteAverage!!)
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w400${item.posterPath}")
                    .placeholder(R.drawable.in_progress)
                    .into(poster)
            textDataView.text = item.releaseDate
            textGenresView.text = item.genreIds
            setOnClickListener {

                Log.d("dd",item.toString())
                val intent = Intent(context,MeFullFilmActivity::class.java)
               intent.putExtra("id",item.id )
               intent.putExtra("title", item.title)
               intent.putExtra("voteAverage", item.voteAverage!!)
               intent.putExtra("popularity",item.popularity)
               intent.putExtra("backdropPath", item.backdropPath)
               intent.putExtra("genreIds", item.genreIds)
               intent.putExtra("posterPath", item.posterPath)
               intent.putExtra("releaseDate", item.releaseDate)
               intent.putExtra("overview", item.overview)
                context.startActivity(intent)
            }
        }
    }
}
