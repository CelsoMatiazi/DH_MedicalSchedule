package com.matiazicelso.medicalschedule.data.repository

import com.matiazicelso.medicalschedule.data.model.ProfileResponse
import com.matiazicelso.medicalschedule.data.netWork.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository(private val api: Api = Api.instance) {
    fun fetchProfile() : Flow<ProfileResponse> = flow {

            emit(api.getProfile())

    }.flowOn(Dispatchers.IO)


    companion object {
        val instance by lazy { UserRepository() }
    }
}