package com.learn2crack.retrofitkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Toast

import com.learn2crack.retrofitkotlin.model.Post
import com.learn2crack.retrofitkotlin.network.RequestInterface
import com.learn2crack.retrofitkotlin.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add.*


class AddActivity : AppCompatActivity() {
 private var mCompositDisposable:CompositeDisposable?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        mCompositDisposable= CompositeDisposable()
    }
    fun addView(view: View){
        if (txtTitle.text.isEmpty() || txtTitle.text.isNullOrEmpty()) {
            showToast("Title is empty..!!")
            return
        }
        if (txtBody.text.isEmpty() || txtBody.text.isNullOrEmpty()) {
            showToast("Body is empty..!!")
            return
        }
        addPost(Post("1",txtTitle.text.toString(),txtBody.text.toString()))

        finish()
    }

    private fun addPost(post:Post) {
        val retrofit= RetrofitClient.instance
        val requestInterface = retrofit.create(RequestInterface::class.java)

        mCompositDisposable?.add(requestInterface.addData(post)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))
    }
    private fun handleResponse(post: Post) {
        showToast("successfully added...!!")
    }

    private fun handleError(error: Throwable) {

        //d = Log.d(TAG, error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositDisposable?.clear()
    }
}
