package com.example.zaynannisa.recyclerviewkotlin.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.zaynannisa.recyclerviewkotlin.R
import com.example.zaynannisa.recyclerviewkotlin.data.User
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlin.math.log

class CustomAdapter(val userList:ArrayList<User>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        Log.d("TAG","This is create view holder create")
        val v=LayoutInflater.from(p0?.context).inflate(R.layout.item_layout,p0,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder?.txtName?.text=userList[position].name
        holder?.txtName?.text=userList[position].title
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName=itemView.findViewById<TextView>(R.id.txtName)
        val txtTitle=itemView.findViewById<TextView>(R.id.txtTitle)
    }
}