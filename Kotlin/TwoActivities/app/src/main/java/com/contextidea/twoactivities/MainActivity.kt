package com.contextidea.twoactivities


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
 private val TAG:String=MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun sendClick(view: View){
        //var intent:Intent=Intent(this,SecondActivity::class.java)
        //intent.putExtra("message",editText.text)
        startActivity(Intent(this,SecondActivity::class.java).apply { putExtra("message",editText.text) })
        startActivity(intent)
        Log.d(TAG, "Button clicked.")
    }
}
