package com.example.zaynannisa.kotlinretrofitrxjava.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.zaynannisa.kotlinretrofitrxjava.R
import com.example.zaynannisa.kotlinretrofitrxjava.data.Post
import kotlinx.android.synthetic.main.post_layout.view.*

class PostAdapter(internal var context:Context,internal var postList:List<Post>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.post_layout,parent,false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.txt_author.text=postList[position].title
        holder.txt_content.text=StringBuilder(postList[position].body.substring(0,20)).append("...").toString()
        holder.txt_author.text=postList[position].userId.toString()
    }
    class PostViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val txt_title= itemView!!.findViewById<TextView>(R.id.txt_tilte)
        val txt_content= itemView!!.findViewById<TextView>(R.id.txt_content)
        val txt_author= itemView!!.findViewById<TextView>(R.id.txt_autor)
    }
}




