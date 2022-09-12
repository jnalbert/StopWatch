package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    // make a class wide static constant in Kotlin
    companion object {
        // all your "static" constants go here
        const val TAG = "MainActivity"
    }

    lateinit var stopWatch : Chronometer
    lateinit var startStopButton: Button
    lateinit var resetButton: Button
    var isRunning = false
    var time = 0.toLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")

        wireWidgets()

        startStopButton.setOnClickListener {
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
        stopWatch.start()
        stopWatch.base = SystemClock.elapsedRealtime() - time
    }

    private fun  onStopPress() {
        startStopButton.text = "Start"
        stopWatch.stop()
        time =  SystemClock.elapsedRealtime() - stopWatch.base
    }

    private fun onResetPress() {
        onStopPress()
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
    }
}