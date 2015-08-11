package com.example.injekt

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.injectLazy
import uy.kohesive.injekt.injectLogger
import java.text.DateFormat
import java.util.Date
import java.util.logging.Logger
import kotlin.properties.Delegates

public class InjektDemoActivity() : AppCompatActivity() {
    private val button by Delegates.lazy { findViewById(R.id.button) }
    private val text by Delegates.lazy { findViewById(R.id.text) as TextView }
    //logger
    private val logger: Logger by Delegates.injectLogger()
    //default location service
    private val locationService by Delegates.injectLazy<LocationService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { printAndSaveLocation() }
    }

    private fun printAndSaveLocation() {
        val location = locationService.getLocation()
        text.setText("${Injekt.get<DateFormat>().format(Date())}\n")
        text.append("Location: ${location.first}, ${location.second}")
        saveLastLocation(location = location)
    }

    private fun saveLastLocation(prefs: SharedPreferences = Injekt.get(getLocalClassName()), location: Pair<String, String>) {
        logger.info("Saving location $location")
        with(prefs.edit()) {
            putString("location_lat", location.first)
            putString("location_lon", location.second)
            commit()
        }
    }
}

