package com.example.bigi.kinotop

import android.annotation.SuppressLint
import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.action_top-> transitionFragment(FilmTopFragment())
                R.id.action_random->  transitionFragment(RandomFragment())
                R.id.action_user->  transitionFragment(UserFragment())
            }
            true
        }



    }
    private fun transitionFragment(newFragment: Fragment){
        val ft =  fragmentManager.beginTransaction()
        ft.replace(R.id.fragments,newFragment)
        ft.commit()
    }
}
