package com.bigmeco.bigi.kinotop

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_random.*
import android.view.animation.Animation.AnimationListener
import com.bigmeco.bigi.kinotop.data.NewFilm
import com.bigmeco.bigi.kinotop.model.MyFilm
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import java.util.*


class RandomFragment : Fragment() {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requesRrandomFilm(false)

        spinner.adapter = ArrayAdapter<String>(activity, R.layout.list_genre, continText())
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            var i = 1
            override fun onItemSelected(parent: AdapterView<*>?, viewv: View?, position: Int, id: Long) {
                spinnerOnClick(parent, position)
                if (i > 1) {
                    spinnerGenreClick(parent, position)
                }
                i++
            }
        }
    }

    fun spinnerGenreClick(parent: AdapterView<*>?, position: Int) {
        requesRrandomGenreFilm(parent, position)
        changeFilm()
        if (position == 0) {
            requesRrandomFilm(true)
            changeFilm()
        }

    }

    fun spinnerOnClick(parent: AdapterView<*>?, position: Int) {
        floatingRepit.setOnClickListener {
            requesRrandomGenreFilm(parent, position)
            changeFilm()
        }
        if (position == 0) {
            floatingRepit.setOnClickListener {
                requesRrandomFilm(true)
                changeFilm()
            }
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
            }
        }
        return i
    }


    private fun changeFilm() {
        val animOld = AnimationUtils.loadAnimation(activity, R.anim.repit_film)
        fabSpace.startAnimation(animOld)
        animOld.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(anim: Animation) {
                floatingLike.isEnabled = false
                floatingRepit.isEnabled = false

            }
            override fun onAnimationRepeat(anim: Animation) {}
            override fun onAnimationEnd(anim: Animation) {
                fabSpace.visibility = View.INVISIBLE
            }
        })

    }

    private fun requesRrandomFilm(newOpen: Boolean, parent: AdapterView<*>? = null, position: Int = 0) {
        val random = Random()
        Service.getFilm().randomFilm(random.nextInt(999) + 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    resultFilm(result, random, newOpen)
                }, { error ->
                    error.printStackTrace()
                })

    }

    private fun requesRrandomGenreFilm(parent: AdapterView<*>?, position: Int) {
        val random = Random()
        if (parent != null) {
            Service.getFilm().randomGenresFilm(
                    random.nextInt(
                            continGenre(parent.getItemAtPosition(position).toString()).get(0)) + 1,
                    continGenre(parent.getItemAtPosition(position).toString()).get(1))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        resultFilm(result, random, true, parent, position)
                    }, { error ->
                        error.printStackTrace()
                    })
        }
    }

    fun resultFilm(result: NewFilm, random: Random, newOpen: Boolean, parent: AdapterView<*>? = null, position: Int = 0) {
        val animNew = AnimationUtils.loadAnimation(activity, R.anim.rept_new_film)
        val randomRezult = result.results!!.get(random.nextInt(19))!!
        textNameView.text = randomRezult.title
        textDataView.text = randomRezult.releaseDate
        ratingView.setValueAnimated(randomRezult.voteAverage!!, 1500)
        popularityView.setValueAnimated(randomRezult.popularity!!, 1500)
        textGenresView.text = "Жанры: ${continText(randomRezult.genreIds)}"
        textInfoView.text = randomRezult.overview
        Picasso.with(activity)
                .load("https://image.tmdb.org/t/p/w400${randomRezult.posterPath}")
                .placeholder(R.drawable.in_progress)
                .into(posterView)
        Picasso.with(activity)
                .load("https://image.tmdb.org/t/p/w500${randomRezult.backdropPath}")
                .placeholder(R.drawable.defaultes)
                .into(backgroundImageView)
        if (newOpen) {
            fabSpace.startAnimation(animNew)
            animNew.setAnimationListener(object : AnimationListener {
                override fun onAnimationStart(anim: Animation) { }
                override fun onAnimationRepeat(anim: Animation) {}
                override fun onAnimationEnd(anim: Animation) {
                    floatingLike.isEnabled = true
                    floatingRepit.isEnabled = true

                }
            })
            fabSpace.visibility = View.VISIBLE
        }

        val realm: Realm = Realm.getDefaultInstance()
        if (realm.where(MyFilm::class.java).equalTo("id", randomRezult.id.toString()).findAll().size == 0) {
            floatingLike.setImageResource(R.drawable.heart_outline)
            floatingLike.setOnClickListener {
                floatingLike.setImageResource(R.drawable.heart)
                val newLoveFilm = MyFilm()
                newLoveFilm.title = randomRezult.title
                newLoveFilm.voteCount = randomRezult.voteCount
                newLoveFilm.id = randomRezult.id.toString()
                newLoveFilm.video = randomRezult.video
                newLoveFilm.voteAverage = randomRezult.voteAverage
                newLoveFilm.popularity = randomRezult.popularity
                newLoveFilm.posterPath = randomRezult.posterPath
                newLoveFilm.originalLanguage = randomRezult.originalLanguage
                newLoveFilm.originalTitle = randomRezult.originalTitle
                newLoveFilm.genreIds = "Жанры:  ${continText(randomRezult.genreIds)}"
                newLoveFilm.backdropPath = randomRezult.backdropPath
                newLoveFilm.adult = randomRezult.adult
                newLoveFilm.overview = randomRezult.overview
                newLoveFilm.releaseDate = randomRezult.releaseDate
                realm.executeTransaction { realm ->
                    realm.insert(newLoveFilm)
                }

                if (position == 0) {
                    requesRrandomFilm(true)
                    changeFilm()
                } else {
                    requesRrandomGenreFilm(parent, position)
                    changeFilm()
                }
            }
        } else {
            floatingLike.setImageResource(R.drawable.heart)
            floatingLike.setOnClickListener {
                realm.executeTransaction { realm ->
                    realm.where(MyFilm::class.java).equalTo("id", randomRezult.id.toString()).findFirst()!!.deleteFromRealm()
                }
                floatingLike.setImageResource(R.drawable.heart_outline)
                if (position == 0) {
                    requesRrandomFilm(true)
                    changeFilm()
                } else {
                    requesRrandomGenreFilm(parent, position)
                    changeFilm()
                }
            }

        }
    }
}


