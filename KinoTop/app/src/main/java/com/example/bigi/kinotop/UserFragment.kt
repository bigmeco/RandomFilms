package com.example.bigi.kinotop


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class UserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return  LayoutInflater.from(container?.context).inflate(R.layout.fragment_user, container, false)
    }


}
