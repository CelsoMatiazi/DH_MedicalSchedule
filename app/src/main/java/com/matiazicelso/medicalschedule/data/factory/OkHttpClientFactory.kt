package com.matiazicelso.medicalschedule.data.factory

import com.matiazicelso.medicalschedule.BuildConfig
import com.matiazicelso.medicalschedule.data.iterceptor.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    fun build() : OkHttpClient = OkHttpClient.Builder().apply {

        addInterceptor(TokenInterceptor())

        if(BuildConfig.DEBUG){
            val logginInterceptor = HttpLoggingInterceptor()
            logginInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(logginInterceptor)
        }

        readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)

    }.build()

    private const val DEFAULT_TIMEOUT = 60L

}
