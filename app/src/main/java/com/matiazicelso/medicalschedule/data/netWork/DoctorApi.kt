package com.matiazicelso.medicalschedule.data.netWork
import com.matiazicelso.medicalschedule.data.factory.GsonFactory
import com.matiazicelso.medicalschedule.data.factory.OkHttpClientFactory
import com.matiazicelso.medicalschedule.data.factory.RetrofitFactoryDoctor
import com.matiazicelso.medicalschedule.data.model.DoctorResponse
import retrofit2.http.GET
import retrofit2.http.Header


interface DoctorApi {

    @GET("doctor")
    suspend fun getDoctor(
        @Header("Content-Type") content: String = "application/json",
        @Header("Authorization") authorization: String,
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