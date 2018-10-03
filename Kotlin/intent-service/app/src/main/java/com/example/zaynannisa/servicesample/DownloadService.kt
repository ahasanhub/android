package com.example.zaynannisa.servicesample

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import android.os.Environment

import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.net.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


/**
 * Creates an IntentService.  Invoked by your subclass's constructor.
 *
 */
class DownloadService : IntentService("DownloadService") {
    var result = Activity.RESULT_CANCELED

    override fun onHandleIntent(intent: Intent?) {
        val urlPath = intent!!.getStringExtra(URL)
        val fileName = intent.getStringExtra(FILENAME)
        val output = File(Environment.getExternalStorageDirectory(), fileName)
        if (output.exists()) {
            output.delete()
        }
        var stream: InputStream? = null
        var outputStream: FileOutputStream? = null
        try {

            val url = URL(urlPath)
            val connection = url.openConnection() as HttpURLConnection
            stream = BufferedInputStream(connection.inputStream)
            //stream=url.openConnection().getInputStream();
            //val reader = InputStreamReader(stream)
            outputStream = FileOutputStream(output.path)
//            var next = -1
//            while (reader.read()) {
//                outputStream.write(next)
//                //successfully finished
//            }
            stream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            result = Activity.RESULT_OK
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                if (outputStream != null) {
                    try {
                        outputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
        publishResult(output.absolutePath, result)
    }

    private fun publishResult(outputPath: String, result: Int) {
        val intent = Intent(NOTIFICATION)
        intent.putExtra(FILEPATH, outputPath)
        intent.putExtra(RESULT, result)
        sendBroadcast(intent)
    }

    companion object {
        val NOTIFICATION = "com.example.zaynannisa.servicesample"
        val FILEPATH = "filepath"
        val RESULT = "result"
        val URL = "urlpath"
        val FILENAME = "filename"
    }
}
