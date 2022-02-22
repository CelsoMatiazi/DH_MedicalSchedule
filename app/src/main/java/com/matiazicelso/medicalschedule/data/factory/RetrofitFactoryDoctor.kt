package com.matiazicelso.medicalschedule.data.factory

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactoryDoctor {

    fun build(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://dh-digital-doctor-api.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}