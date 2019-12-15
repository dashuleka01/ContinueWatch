package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0

    companion object {
        val NUM = "number"
    }

    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            textSecondsElapsed.post {
                textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        backgroundThread.start()
        Log.v("Threadtest", "OnCreate: " + backgroundThread.state.toString())
    }

    override fun onStart() {
        super.onStart()
        loadSeconds()
        Log.v("Threadtest", "OnStart: " + backgroundThread.state.toString())
    }

    override fun onStop() {
        super.onStop()
        saveSeconds()
        Log.v("Threadtest", "OnStop: " + backgroundThread.state.toString())
    }

    override fun onPause() {
        super.onPause()
        Log.v("Threadtest", "OnPause: " + backgroundThread.state.toString())
    }

    override fun onResume() {
        super.onResume()
        Log.v("Threadtest", "OnResume: " + backgroundThread.state.toString())
    }

    private fun saveSeconds(){
        val sPref = getPreferences(Context.MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sPref.edit()
        ed.putInt(NUM, secondsElapsed)
        ed.commit()
        Log.v("myapptest", "saveSeconds")
    }

    private fun loadSeconds(){
        val sPref = getPreferences(Context.MODE_PRIVATE)
        secondsElapsed = sPref.getInt(NUM, 0)
        Log.v("myapptest", "loadSeconds")
    }
}
