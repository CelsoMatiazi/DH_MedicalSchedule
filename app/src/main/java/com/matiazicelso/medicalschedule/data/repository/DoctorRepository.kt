package com.matiazicelso.medicalschedule.data.repository

import com.matiazicelso.medicalschedule.data.factory.DatabaseFactory
import com.matiazicelso.medicalschedule.data.model.DoctorItem
import com.matiazicelso.medicalschedule.data.model.DoctorResponse
import com.matiazicelso.medicalschedule.data.model.toDoctorEntity
import com.matiazicelso.medicalschedule.data.netWork.DataResult
import com.matiazicelso.medicalschedule.data.netWork.DoctorApi
import com.matiazicelso.medicalschedule.data.netWork.upDateStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class DoctorRepository(
    private val api: DoctorApi = DoctorApi.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private val dao = DatabaseFactory.getDatabase().doctorDao()

    fun fetchDoctor(page: Int) : Flow<DataResult<DoctorResponse>> = flow<DataResult<DoctorResponse>> {

        val localItems = dao.listAll()

        val response = api.getDoctor(page)
        val newList = response.doctors.map { apiItem ->
            if(localItems.filter { it.apiId == apiItem.id }.getOrNull(0) != null) apiItem.copy(
                isFavorite = true
            ) else apiItem }
        emit(DataResult.Success(response.copy(doctors = newList)))
    }.upDateStatus() // extension created
        .flowOn(dispatcher)

    fun addOrRemoveFavorite(item: DoctorItem) = flow {
        try {
            val numberOfRegister = dao.countApiId(item.id)
            val itemExist = numberOfRegister >= 1

            if(itemExist){
                dao.deleteByApiId(item.id)
            }else{
                dao.insert(item.toDoctorEntity())
            }

            //DatabaseFactory.getDatabase().doctorDao().insert(item.toDoctorEntity())
            emit(DataResult.Success(item.copy(isFavorite = itemExist.not())))
        }catch (e: Exception){
            emit(DataResult.Error(IllegalAccessException()))
        }
    }.upDateStatus().flowOn(dispatcher)


    companion object {
        val instance by lazy { DoctorRepository() }
    }
}