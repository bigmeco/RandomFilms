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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.film_top_fragment.*


class RandomFragment : Fragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner.adapter = ArrayAdapter<String>(activity, R.layout.list_genre, continText())
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                floatingRepit.setOnClickListener { Log.d("ggg", position.toString()) }
                if (position == 0) {
                    floatingRepit.setOnClickListener {
                        Log.d("fff", position.toString())

                        changeFilm()
                        Service.getFilm().listNewFilm()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe({ result ->
                                    textNameView.text = result.results!!.get(0)!!.title
                                }, { error ->
                                    error.printStackTrace()
                                })
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


    private fun changeFilm() {
        val animOld = AnimationUtils.loadAnimation(activity, R.anim.repit_film)
        val animNew = AnimationUtils.loadAnimation(activity, R.anim.rept_new_film)
        fabSpace.startAnimation(animOld)
        animOld.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(anim: Animation) {}
            override fun onAnimationRepeat(anim: Animation) {}
            override fun onAnimationEnd(anim: Animation) {
                fabSpace.startAnimation(animNew)
            }
        })

    }
}
