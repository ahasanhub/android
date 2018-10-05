package com.example.zaynannisa.serviceanother

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

import java.lang.reflect.Array
import java.util.ArrayList
import java.util.Random

class LocalWordService : Service() {
    private val listResult = ArrayList<String>()
    private var counter = 1
    private val mBinder = MyBinder()

    val wordList: List<String>
        get() = listResult

    inner class MyBinder : Binder() {
        internal val service: LocalWordService
            get() = this@LocalWordService
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        addResultValues()
        return Service.START_NOT_STICKY
    }

    private fun addResultValues() {
        val random = Random()
        val input = ArrayList<String>()
        input.add("Linux")
        input.add("Android")
        input.add("Windows")
        input.add("Mac")
        listResult.add(input[random.nextInt(3)] + " " + counter++)
        if (counter == Integer.MAX_VALUE) {
            counter = 0
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        addResultValues()
        return mBinder
    }
}
