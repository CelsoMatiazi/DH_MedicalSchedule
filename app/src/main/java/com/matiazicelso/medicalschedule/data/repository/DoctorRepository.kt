package com.matiazicelso.medicalschedule.data.repository

import com.matiazicelso.medicalschedule.data.model.DoctorResponse
import com.matiazicelso.medicalschedule.data.netWork.DoctorApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DoctorRepository(private val api: DoctorApi = DoctorApi.instance) {

    fun fetchDoctor(page: Int) : Flow<DoctorResponse> = flow {
        emit(api.getDoctor(page))
    }.flowOn(Dispatchers.IO)


    companion object {
        val instance by lazy { DoctorRepository() }
    }
}