package com.example.injekt

import android.app.Application
import uy.kohesive.injekt.Injekt

public class InjektDemoApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        //Injecting module
        Injekt.importModule(InjektDemoModule(this))
    }
}