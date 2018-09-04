package com.learn2crack.retrofitkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

import com.learn2crack.retrofitkotlin.network.RequestInterface
import com.learn2crack.retrofitkotlin.adapter.DataAdapter
import com.learn2crack.retrofitkotlin.model.Post
import com.learn2crack.retrofitkotlin.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), DataAdapter.Listener {

    private val TAG = MainActivity::class.java.simpleName

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mAndroidArrayList: ArrayList<Post>? = null

    private var mAdapter: DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            startActivity(Intent(this,AddActivity::class.java))
        }

        mCompositeDisposable = CompositeDisposable()

        initRecyclerView()

        loadJSON()

    }

    private fun initRecyclerView() {

        recyclerView1.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView1.layoutManager = layoutManager
    }

    private fun loadJSON() {
        val retrofit=RetrofitClient.instance
        val requestInterface = retrofit.create(RequestInterface::class.java)

        mCompositeDisposable?.add(requestInterface.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))

    }

    private fun handleResponse(androidList: List<Post>) {

        mAndroidArrayList = ArrayList(androidList)
        mAdapter = DataAdapter(mAndroidArrayList!!, this)

        recyclerView1.adapter = mAdapter
    }

    private fun handleError(error: Throwable) {

        Log.d(TAG, error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(post: Post) {

        Toast.makeText(this, "${post.title} Clicked !", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}
