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
import com.example.stopwatch.MainActivity.Companion.STATE_IS_RUNNING
import com.example.stopwatch.MainActivity.Companion.STATE_TIME

class MainActivity : AppCompatActivity() {

    // make a class wide static constant in Kotlin
    companion object {
        // all your "static" constants go here
        const val TAG = "MainActivity"
        const val STATE_IS_RUNNING = "sate_is_running"
        const val STATE_TIME = "state_time"
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
        // checks if a saved instance state exists and sets the variables accordingly
        if (savedInstanceState != null) {
            // sets the instance variables with the saved state
            isRunning = savedInstanceState.getBoolean(STATE_IS_RUNNING)
            time = savedInstanceState.getLong(STATE_TIME)
            restartWatchAfterChange()
        }

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

    private fun restartWatchAfterChange() {
        // restarts the watch after a change in time
        stopWatch.base = SystemClock.elapsedRealtime() - time
        // if the stopwatch was running start it again if it wasn't stop it
        if (isRunning) onStartPress()
        else onStopPress()
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

    override fun onSaveInstanceState(outState: Bundle) {
        // preserves state through orientation changes

        if (isRunning) time = SystemClock.elapsedRealtime() - stopWatch.base
        // save key value pairs to the bundle
        outState.run {
            putBoolean(STATE_IS_RUNNING, isRunning)
            putLong(STATE_TIME, time)
        }
        super.onSaveInstanceState(outState)
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