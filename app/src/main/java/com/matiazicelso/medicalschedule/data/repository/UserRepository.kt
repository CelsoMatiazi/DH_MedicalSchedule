package com.matiazicelso.medicalschedule.data.repository

import com.matiazicelso.medicalschedule.data.model.ProfileResponse
import com.matiazicelso.medicalschedule.data.netWork.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class UserRepository(private val api: Api = Api.instance) {
    fun fetchProfile() : Flow<ProfileResponse> = flow {

            emit(api.getProfile())

    }.flowOn(Dispatchers.IO)


    companion object {
        val instance by lazy { UserRepository() }
    }
}

//fun fetchProfile() : Flow<DataResult<ProfileResponse>> = flow<DataResult<ProfileResponse>> {
//    emit(DataResult.Success(api.getProfile()))
//}.catch { e ->  emit(DataResult.Error(e)) }
//    .onStart { emit(DataResult.Loading(true)) }
//    .onCompletion { emit(DataResult.Loading(false)) }
//    .flowOn(Dispatchers.IO)