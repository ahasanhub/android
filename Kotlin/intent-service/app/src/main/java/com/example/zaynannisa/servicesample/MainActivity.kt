package com.example.zaynannisa.servicesample

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val recever = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val bundle = intent.extras
            if (bundle != null) {
                val string = bundle.getString(DownloadService.FILEPATH)
                val resultCode = bundle.getInt(DownloadService.RESULT)
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this@MainActivity,
                            "Download complete. Download URI: " + string!!,
                            Toast.LENGTH_LONG).show()
                    status!!.text = "Download done"
                } else {
                    Toast.makeText(this@MainActivity, "Download failed",
                            Toast.LENGTH_LONG).show()
                    status!!.text = "Download failed"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(recever, IntentFilter(DownloadService.NOTIFICATION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(recever)
    }

    fun onClick(view: View) {
        val intent = Intent(this, DownloadService::class.java)
        // add infos for the service which file to download and where to store
        intent.putExtra(DownloadService.FILENAME, "index.html")
        intent.putExtra(DownloadService.URL,
                "http://www.android.com/")
        startService(intent)
        status!!.text = "Service started"
    }
}
