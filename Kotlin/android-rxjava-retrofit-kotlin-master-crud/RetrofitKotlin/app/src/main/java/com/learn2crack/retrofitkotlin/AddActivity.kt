package com.learn2crack.retrofitkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
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
        showToast("successfully added...!!")
        finish()
    }
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
