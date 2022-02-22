package com.matiazicelso.medicalschedule.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiazicelso.medicalschedule.data.model.UserProfile
import com.matiazicelso.medicalschedule.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: UserRepository = UserRepository.instance): ViewModel() {

    private val _profile = MutableLiveData<UserProfile>()
    val profile: LiveData<UserProfile>
        get() = _profile


    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading


    //private val _error = SingleEventLiveData<Boolean>()
    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

        @RequiresApi(Build.VERSION_CODES.O)
        fun loadProfile(){
            viewModelScope.launch(Dispatchers.IO) {
                repository.fetchProfile()
                    .onStart { _loading.postValue(true) }
                    .catch { _error.postValue(true) }
                    //.onCompletion { _loading.postValue(false) }
                    .collect {
                        val result = it.results.first()
                        _profile.postValue(UserProfile(result))
                        _loading.postValue(false)
                        delay(5000)
                        _loading.postValue(true)
                    }

            }
        }
}