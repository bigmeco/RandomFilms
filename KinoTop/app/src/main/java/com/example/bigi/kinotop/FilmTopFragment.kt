package com.example.bigi.kinotop


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bigi.kinotop.data.NewFilmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.film_top_fragment.*


class FilmTopFragment : Fragment() {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoadingView.spin()
        Service.getFilm().listNewFilm()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    LoadingView.stopSpinning()
                    LoadingView.visibility = View.INVISIBLE
                    topFilmList.layoutManager = LinearLayoutManager(activity)
                    topFilmList.adapter = TopFilmAdapter(result.results as List<NewFilmData>, { (item) -> })
                }, { error ->
                    error.printStackTrace()
                })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return LayoutInflater.from(container?.context).inflate(R.layout.film_top_fragment, container, false)

    }


}
