package com.matiazicelso.medicalschedule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiazicelso.medicalschedule.data.model.DoctorResponse
import com.matiazicelso.medicalschedule.data.model.UserSettings
import com.matiazicelso.medicalschedule.data.repository.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DoctorViewModel(
    private val repository: DoctorRepository = DoctorRepository.instance) : ViewModel() {

    private val _doctors = MutableLiveData<DoctorResponse>()
    val doctors: LiveData<DoctorResponse>
        get() = _doctors

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error


    fun loadDoctor(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchDoctor(UserSettings.token ?: "")
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .collect {
                    val result = it
                    _doctors.postValue(result)
                }

        }
    }

}