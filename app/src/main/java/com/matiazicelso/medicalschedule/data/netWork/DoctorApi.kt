package com.matiazicelso.medicalschedule.data.netWork

import com.matiazicelso.medicalschedule.data.factory.GsonFactory
import com.matiazicelso.medicalschedule.data.factory.OkHttpClientFactory
import com.matiazicelso.medicalschedule.data.factory.RetrofitFactoryDoctor
import com.matiazicelso.medicalschedule.data.model.DoctorResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface DoctorApi {

    @GET("doctor")
    suspend fun getDoctor(
        @Query("page") page: Int
    ): DoctorResponse

    companion object{
        val instance: DoctorApi by lazy {
            RetrofitFactoryDoctor.build(
                OkHttpClientFactory.build(),
                GsonFactory.build()
            ).create(DoctorApi::class.java)
        }
    }
}