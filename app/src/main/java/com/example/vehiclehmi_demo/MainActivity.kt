package com.example.vehiclehmidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        refreshButton = findViewById(R.id.refreshButton)

        refreshButton.setOnClickListener {
            statusText.text = "Refreshing vehicle status..."
            thread { simulateVehicleDataFetch() }  // background thread
        }
    }

    private fun simulateVehicleDataFetch() {
        Thread.sleep(1500) // simulate data delay
        runOnUiThread {
            statusText.text = "Engine: ON\nSpeed: 42 km/h\nFuel: 71%"
        }
    }
}
