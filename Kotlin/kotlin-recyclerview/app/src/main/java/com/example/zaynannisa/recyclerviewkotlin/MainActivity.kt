package com.example.zaynannisa.recyclerviewkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.zaynannisa.recyclerviewkotlin.adapter.CustomAdapter
import com.example.zaynannisa.recyclerviewkotlin.data.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv=findViewById<RecyclerView>(R.id.recyclerView1)
        rv.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val users=ArrayList<User>()
        users.add(User("Ahasan","Mr"))
        users.add(User("Shapla","Mrs"))
        users.add(User("Zayn","Miss"))

        rv.adapter=CustomAdapter(users)
    }
}
