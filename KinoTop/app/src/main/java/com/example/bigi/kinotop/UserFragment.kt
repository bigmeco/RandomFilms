package com.example.bigi.kinotop


import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bigi.kinotop.model.MyFilm
import io.realm.Realm
import kotlinx.android.synthetic.main.film_top_fragment.*

class UserFragment : Fragment() {


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val realm: Realm = Realm.getDefaultInstance()

                    topFilmList.layoutManager = LinearLayoutManager(activity)
        topFilmList.adapter = MyFilmAdapter(realm.where(MyFilm::class.java).findAll())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return  LayoutInflater.from(container?.context).inflate(R.layout.fragment_user, container, false)
    }


}
