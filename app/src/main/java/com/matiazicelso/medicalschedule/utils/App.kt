package com.matiazicelso.medicalschedule.utils

import android.app.Application
import android.content.Context

import com.matiazicelso.medicalschedule.data.factory.DatabaseFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseFactory.build(this)
        //AppEventsLogger.activateApp(this);
        appContext = applicationContext
    }

    companion object{
        var appContext: Context? = null
    }

}