package com.bigmeco.bigi.kinotop

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigmeco.bigi.kinotop.model.MyFilm
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_top_layout.view.*
import io.realm.Realm


class MyFilmAdapter(val items: List<MyFilm>, val listener: () -> Unit) : RecyclerView.Adapter<MyFilmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_top_layout, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MyFilm, listener: () -> Unit) = with(itemView) {
            val realm: Realm = Realm.getDefaultInstance()
            textName.text = item.title
            like.setImageResource(R.drawable.heart)
            like.setOnClickListener {
                realm.executeTransaction { realm ->
                    realm.where(MyFilm::class.java).equalTo("id", item.id.toString()).findFirst()!!.deleteFromRealm()
                }
                listener.invoke()
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

            ratingView.setValueAnimated(item.voteAverage!!, 1500)
            //ratingView.setValue(item.voteAverage!!)
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w400${item.posterPath}")
                    .placeholder(R.drawable.in_progress)
                    .into(poster)
            textDataView.text = item.releaseDate
            textGenresView.text = item.genreIds
            setOnClickListener {

                val intent = Intent(context, MeFullFilmActivity::class.java)
                intent.putExtra("id", item.id)
                intent.putExtra("title", item.title)
                intent.putExtra("voteAverage", item.voteAverage!!)
                intent.putExtra("popularity", item.popularity)
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
