package com.example.injekt

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.text.format.DateUtils
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import java.text.DateFormat
import java.util.logging.Level
import java.util.logging.Logger

public class InjektDemoModule(val app: Application) : InjektModule {
    override fun InjektRegistrar.registerInjectables() {
        //Adding default location service
        addSingletonFactory<LocationService> {
            LocationServiceImpl(app.getSystemService(Context.LOCATION_SERVICE) as LocationManager, LocationManager.NETWORK_PROVIDER)
        }

        addPerKeyFactory<SharedPreferences,String> { name: String ->
            app.getSharedPreferences(name,Context.MODE_PRIVATE)
        }

        addPerThreadFactory<DateFormat> {
            DateFormat.getDateTimeInstance()
        }

        //Basic logging
        addLoggerFactory(
                { byName ->
                    with(Logger.getLogger(byName)) {
                        setLevel(if (BuildConfig.DEBUG) Level.ALL else Level.WARNING)
                        this
                    }
                },
                { byClass ->
                    with(Logger.getLogger(byClass.getSimpleName())) {
                        setLevel(if (BuildConfig.DEBUG) Level.ALL else Level.WARNING)
                        this
                    }
                })
    }
}