package com.matiazicelso.medicalschedule.viewModel

import androidx.lifecycle.*
import com.matiazicelso.medicalschedule.data.model.DoctorItem
import com.matiazicelso.medicalschedule.data.model.DoctorResponse
import com.matiazicelso.medicalschedule.data.netWork.DataResult
import com.matiazicelso.medicalschedule.data.repository.DoctorRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class DoctorViewModel(
    private val repository: DoctorRepository = DoctorRepository.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {


    fun loadDoctor(page: Int): LiveData<DataResult<DoctorResponse>> =
        repository.fetchDoctor(page).asLiveData()

    fun addOrRemoveFavorite(doctor: DoctorItem) = repository.addOrRemoveFavorite(doctor).flowOn(dispatcher).asLiveData()



}