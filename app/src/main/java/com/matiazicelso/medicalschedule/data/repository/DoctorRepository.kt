package com.matiazicelso.medicalschedule.data.repository

import com.matiazicelso.medicalschedule.data.model.DoctorResponse
import com.matiazicelso.medicalschedule.data.netWork.DataResult
import com.matiazicelso.medicalschedule.data.netWork.DoctorApi
import com.matiazicelso.medicalschedule.data.netWork.upDateStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class DoctorRepository(private val api: DoctorApi = DoctorApi.instance) {

//    fun fetchDoctor(page: Int) : Flow<DoctorResponse> = flow {
//        emit(api.getDoctor(page))
//    }.flowOn(Dispatchers.IO)

    fun fetchDoctor(page: Int) : Flow<DataResult<DoctorResponse>> = flow<DataResult<DoctorResponse>> {
        emit(DataResult.Success(api.getDoctor(page)))
    }
        .upDateStatus() // extension created
        .flowOn(Dispatchers.IO)


    companion object {
        val instance by lazy { DoctorRepository() }
    }
}