package com.example.bigi.kinotop

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_top_layout.view.*


class TopFilmAdapter(val items: ArrayList<TopFilmData>, val listener: (TopFilmData) -> Unit) : RecyclerView.Adapter<TopFilmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_top_layout,parent,false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TopFilmData, listener: (TopFilmData) -> Unit) = with(itemView) {
            textName.setText("gg")
            setOnClickListener { listener(item) }
        }
    }
}