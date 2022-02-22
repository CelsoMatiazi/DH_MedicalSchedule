package com.matiazicelso.medicalschedule.data.netWork
import com.matiazicelso.medicalschedule.data.factory.GsonFactory
import com.matiazicelso.medicalschedule.data.factory.OkHttpClientFactory
import com.matiazicelso.medicalschedule.data.factory.RetrofitFactory
import com.matiazicelso.medicalschedule.data.factory.RetrofitFactoryDoctor
import com.matiazicelso.medicalschedule.data.model.LoginResponse
import com.matiazicelso.medicalschedule.data.model.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiLogin {

    @POST("login")
    open fun createUser(@Body user: UserLogin): Call<LoginResponse>

    suspend fun login(): LoginResponse
    companion object{
        val instance: ApiLogin by lazy {
            RetrofitFactoryDoctor.build(
                OkHttpClientFactory.build(),
                GsonFactory.build()
            ).create(ApiLogin::class.java)
        }
    }

}