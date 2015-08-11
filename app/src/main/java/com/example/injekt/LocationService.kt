package com.example.injekt

import android.location.LocationManager
import uy.kohesive.injekt.injectLogger
import java.util.logging.Logger
import kotlin.properties.Delegates

public interface LocationService {
    public fun getLocation() : Pair<String,String>
}

public class LocationServiceImpl(private val manager: LocationManager,
                                 private val provider: String) : LocationService {
    val logger by Delegates.injectLogger<LocationService,Logger>()
    init {
        logger.info("Created location manager")
    }
    override fun getLocation(): Pair<String, String> {
        logger.info("getting location")
        return manager.getLastKnownLocation(provider).let {
            it.getLatitude().toString() to it.getLongitude().toString()
        }
    }
}
