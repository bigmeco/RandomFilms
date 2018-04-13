package com.example.bigi.kinotop


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.film_top_fragment.*

class FilmTopFragment : Fragment() {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var list = ArrayList<TopFilmData>()
        list.add(TopFilmData("dd"))
        list.add(TopFilmData("dd"))
        list.add(TopFilmData("dd"))
        list.add(TopFilmData("dd"))
        list.add(TopFilmData("dd"))

        val repository = Service.getFilm()

        repository.listNewFilm()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
                    Log.d("Result", "There are ${result.results} ")
                }, { error ->
                    error.printStackTrace()
                })
        topFilmList.layoutManager = LinearLayoutManager(activity)
        topFilmList.adapter = TopFilmAdapter(list,{(itam)->})
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return LayoutInflater.from(container?.context).inflate(R.layout.film_top_fragment, container, false)

    }


}
