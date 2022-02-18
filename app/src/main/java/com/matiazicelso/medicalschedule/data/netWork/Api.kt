package com.matiazicelso.medicalschedule.data.netWork

import com.matiazicelso.medicalschedule.data.factory.GsonFactory
import com.matiazicelso.medicalschedule.data.factory.OkHttpClientFactory
import com.matiazicelso.medicalschedule.data.factory.RetrofitFactory
import com.matiazicelso.medicalschedule.data.model.ProfileResponse
import retrofit2.http.GET

interface Api {

    @GET("api")
    suspend fun getProfile(): ProfileResponse

    companion object{
        val instance: Api by lazy {
            RetrofitFactory.build(
                OkHttpClientFactory.build(),
                GsonFactory.build()

            ).create(Api::class.java)
        }
    }

}