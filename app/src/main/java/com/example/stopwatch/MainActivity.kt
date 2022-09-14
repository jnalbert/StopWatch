package com.example.stopwatch

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    // make a class wide static constant in Kotlin
    companion object {
        // all your "static" constants go here
        const val TAG = "MainActivity"
    }

    lateinit var stopWatch : Chronometer
    lateinit var startStopButton: Button
    lateinit var resetButton: Button
    lateinit var lapButton: Button
    lateinit var lapListView: ListView
    lateinit var lapList: ArrayList<String>
    var isRunning = false
    var time = 0.toLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")

        wireWidgets()

        startStopButton.setBackgroundColor(Color.GREEN)

        startStopButton.setOnClickListener {
            // calls the correct function according to weather the stopwatch is running or not
            if (isRunning) onStopPress()
            else onStartPress()
            isRunning = !isRunning
        }
        resetButton.setOnClickListener {
            onResetPress()
            isRunning = false
        }
    }

    private fun onStartPress() {
        startStopButton.text = "Stop"
        startStopButton.setBackgroundColor(Color.RED)
        stopWatch.start()
        // resets the base of the chronometer to get the correct time
        stopWatch.base = SystemClock.elapsedRealtime() - time
    }

    private fun  onStopPress() {
        startStopButton.text = "Start"
        startStopButton.setBackgroundColor(Color.GREEN)
        stopWatch.stop()
        // saves the time that the stopwatch was stopped at
        time =  SystemClock.elapsedRealtime() - stopWatch.base
    }

    private fun onResetPress() {
        onStopPress()
        // rests the time and base value of the chronometer
        time = 0.toLong()
        stopWatch.base = SystemClock.elapsedRealtime()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    private fun wireWidgets() {
        stopWatch = findViewById(R.id.chronometer_main_stopwatch)
        startStopButton = findViewById(R.id.button_main_startStop)
        resetButton = findViewById(R.id.button_main_reset)
        lapButton = findViewById(R.id.button_main_lap)
        lapListView = findViewById(R.id.listView_main_lapList)
        lapList = ArrayList<String>()
    }
}