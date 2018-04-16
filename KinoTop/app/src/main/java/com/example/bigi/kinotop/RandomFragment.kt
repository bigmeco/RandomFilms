package com.example.bigi.kinotop

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_random.*


class RandomFragment : Fragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner.adapter = ArrayAdapter<String>(activity,R.layout.list_genre,continText())
         spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
             override fun onNothingSelected(parent: AdapterView<*>?) {
             }

             override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 floatingRepit.setOnClickListener { Log.d("ggg",position.toString()) }
                 if (position==0) {
                     floatingRepit.setOnClickListener { Log.d("fff",position.toString())
                         val animOld = AnimationUtils.loadAnimation(activity,R.anim.repit_film)
                         fabSpace.setInAnimation(animOld)
                         animOld.
                         val animNew = AnimationUtils.loadAnimation(activity,R.anim.rept_new_film)

                         fabSpace.startAnimation(animNew)

                     }
                 }
             }
         }



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_random, container, false)
    }

    fun continText(): ArrayList<String>  {
        var genres:ArrayList<String> = ArrayList()
        genres.add("Все жанры")
                for (id in GenreList.values()) {
                    genres.add(id.names)
        }
        return genres
    }

}
