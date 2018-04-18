package com.example.bigi.kinotop

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_random.*
import android.view.animation.Animation.AnimationListener
import com.example.bigi.kinotop.data.NewFilmData
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.film_top_fragment.*
import kotlinx.android.synthetic.main.list_top_layout.view.*
import java.util.*


class RandomFragment : Fragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requesRrandomFilm(false)
        spinner.adapter = ArrayAdapter<String>(activity, R.layout.list_genre, continText())
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, viewv: View?, position: Int, id: Long) {
                floatingRepit.setOnClickListener {
                    Log.d("ggg", parent!!.getItemAtPosition(position).toString())
                    requesRrandomGenreFilm(parent,position)
                    changeFilm()

                }
                if (position == 0) {
                    floatingRepit.setOnClickListener {
                        Log.d("fff", position.toString())
                        requesRrandomFilm(true)
                        changeFilm()

                    }
                }
            }
        }
        floatingLike.setOnClickListener {
            floatingLike.setImageResource(R.drawable.heart)

        }


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_random, container, false)
    }

    fun continText(): ArrayList<String> {
        var genres: ArrayList<String> = ArrayList()
        genres.add("Все жанры")
        for (id in GenreList.values()) {
            genres.add(id.names)
        }
        return genres
    }

    fun continGenre(genre: String): Array<Int> {
        var i = arrayOf(100, 16)
        for (id in GenreList.values()) {
            if (id.names == genre) {
                i[0] = id.sizes
                i[1] = id.id
                Log.d("ggg", GenreList.valueOf(id.name).toString())

            }
        }
        return i
    }


    private fun changeFilm() {
        val animOld = AnimationUtils.loadAnimation(activity, R.anim.repit_film)
        fabSpace.startAnimation(animOld)
        animOld.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(anim: Animation) {}
            override fun onAnimationRepeat(anim: Animation) {}
            override fun onAnimationEnd(anim: Animation) {
                fabSpace.visibility = View.INVISIBLE
            }
        })

    }

    private fun requesRrandomFilm(newOpen: Boolean) {
        val random = Random()
        val animNew = AnimationUtils.loadAnimation(activity, R.anim.rept_new_film)

        Service.getFilm().randomFilm(random.nextInt(999) + 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    val randomRezult = result.results!!.get(random.nextInt(19))!!
                    textNameView.text = randomRezult.title
                    textDataView.text = randomRezult.releaseDate
                    ratingView.setValueAnimated(randomRezult.voteAverage!!, 1500)
                    popularityView.setValueAnimated(randomRezult.popularity!!, 1500)
                    textGenresView.text = "Жанры: ${continText(randomRezult.genreIds)}"
                    textInfoView.text = randomRezult.overview
                    if (newOpen) {
                        fabSpace.startAnimation(animNew)
                        fabSpace.visibility = View.VISIBLE
                    }
                    Picasso.with(activity)
                            .load("https://image.tmdb.org/t/p/w400${randomRezult.posterPath}")
                            .placeholder(R.drawable.in_progress)
                            .into(posterView)
                    Picasso.with(activity)
                            .load("https://image.tmdb.org/t/p/w500${randomRezult.backdropPath}")
                            .placeholder(R.drawable.defaultes)
                            .into(backgroundImageView)
                }, { error ->
                    error.printStackTrace()
                })

    }

    private fun requesRrandomGenreFilm(parent: AdapterView<*>?, position: Int) {
        val random = Random()
        val animNew = AnimationUtils.loadAnimation(activity, R.anim.rept_new_film)
        if (parent != null) {
            Service.getFilm().randomGenresFilm(
                    random.nextInt(
                            continGenre(parent.getItemAtPosition(position).toString()).get(0)) + 1,
                    continGenre(parent.getItemAtPosition(position).toString()).get(1))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                            fabSpace.startAnimation(animNew)
                            fabSpace.visibility = View.VISIBLE
                        val randomRezult = result.results!!.get(random.nextInt(19) + 1)!!
                        Picasso.with(activity)
                                .load("https://image.tmdb.org/t/p/w500${randomRezult.posterPath}")
                                .placeholder(R.drawable.in_progress)
                                .into(posterView)
                        Picasso.with(activity)
                                .load("https://image.tmdb.org/t/p/original${randomRezult.backdropPath}")
                                .placeholder(R.drawable.defaultes)
                                .into(backgroundImageView)
                        textNameView.text = randomRezult.title
                        textDataView.text = randomRezult.releaseDate
                        ratingView.setValueAnimated(randomRezult.voteAverage!!, 1500)
                        popularityView.setValueAnimated(randomRezult.popularity!!, 1500)
                        textGenresView.text = "Жанры: ${continText(randomRezult.genreIds)}"
                        textInfoView.text = randomRezult.overview

                    }, { error ->
                        error.printStackTrace()
                    })
        }


    }


}
