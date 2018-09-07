package com.learn2crack.retrofitkotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Color

import com.learn2crack.retrofitkotlin.R
import com.learn2crack.retrofitkotlin.R.id.ibDelete
import com.learn2crack.retrofitkotlin.model.Post

import kotlinx.android.synthetic.main.recycler_view_row.view.*

class DataAdapter (private val dataList : ArrayList<Post>, private val listener : Listener) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    interface Listener {

        fun onItemClick(post : Post)
        fun onItemDeleteClick()
    }

    //private val colors : Array<String> = arrayOf("#EF5350", "#EC407A", "#AB47BC", "#7E57C2", "#5C6BC0", "#42A5F5")

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(dataList[position], listener,  position)
    }

    override fun getItemCount(): Int = dataList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(post: Post, listener: Listener,  position: Int) {
            itemView.txtTitle.text = post.title
            itemView.txtBody.text = StringBuilder(post.body.substring(0,20)).append("...").toString()
            //itemView.setBackgroundColor(Color.parseColor(colors[position % 6]))

            itemView.setOnClickListener{ listener.onItemClick(post) }
            itemView.ibDelete.setOnClickListener{listener.onItemDeleteClick()}
        }
    }
}