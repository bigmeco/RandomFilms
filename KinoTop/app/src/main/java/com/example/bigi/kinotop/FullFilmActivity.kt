package com.example.bigi.kinotop

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.bigi.kinotop.data.NewFilmData
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_scrolling.*

class FullFilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        val filelist = intent.getStringExtra("FILM_INFO")
        val item: NewFilmData = Gson().fromJson(filelist, NewFilmData::class.java)
    }
}
