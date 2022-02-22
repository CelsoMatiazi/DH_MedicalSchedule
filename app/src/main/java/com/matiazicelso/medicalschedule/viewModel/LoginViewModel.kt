package com.matiazicelso.medicalschedule.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiazicelso.medicalschedule.data.model.LoginResponse
import com.matiazicelso.medicalschedule.data.model.LoginSession
import com.matiazicelso.medicalschedule.data.model.UserLogin
import com.matiazicelso.medicalschedule.data.model.UserProfile
import com.matiazicelso.medicalschedule.data.repository.LoginRepository
import com.matiazicelso.medicalschedule.data.repository.RequestApi
import com.matiazicelso.medicalschedule.data.repository.UserRepository
import com.matiazicelso.medicalschedule.utils.SingleEventLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository = LoginRepository.instance): ViewModel() {

    //private val request = RequestApi()

    private val _profile = MutableLiveData<LoginSession>()
    val profile: LiveData<LoginSession>
        get() = _profile

    private val _login = SingleEventLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _login

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error


    fun makeLogin(email: String, password: String){

        val dataLogin = UserLogin(email, password)

        viewModelScope.launch(Dispatchers.IO) {
                loginRepository.fetchLogin()

                    .onStart { _progressBar.postValue(true) }
                    .catch { _error.postValue(true) }
                    .onCompletion { _progressBar.postValue(false) }
                    .collect {
                        val result = it.loginResult
                        _profile.postValue(result)

                    }
        }
    }

}