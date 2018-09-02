package com.example.zaynannisa.kotlinretrofitrxjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.example.zaynannisa.kotlinretrofitrxjava.adapter.PostAdapter
import com.example.zaynannisa.kotlinretrofitrxjava.data.Post
import com.example.zaynannisa.kotlinretrofitrxjava.retrofit.IMyApi
import com.example.zaynannisa.kotlinretrofitrxjava.retrofit.RetrofitClient

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    internal lateinit var jsonApi:IMyApi
    internal lateinit var compositeDisposable: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable=CompositeDisposable()
        //init api
        val retrofit=RetrofitClient.instance
        jsonApi=retrofit.create(IMyApi::class.java)
        //Recycler View
        recyler_post.setHasFixedSize(true)
        recyler_post.layoutManager=LinearLayoutManager(this )
        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(jsonApi.posts
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{posts->displayData(posts)}
        )

    }

    private fun displayData(posts: List<Post>?) {
     val adapter=PostAdapter(this,posts!!)
        recyler_post.adapter=adapter
    }
}
