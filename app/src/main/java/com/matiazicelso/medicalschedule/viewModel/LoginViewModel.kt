package com.matiazicelso.medicalschedule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.matiazicelso.medicalschedule.data.model.*
import com.matiazicelso.medicalschedule.data.repository.LoginRepository
import com.matiazicelso.medicalschedule.utils.SingleEventLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val loginRepository: LoginRepository = LoginRepository.instance
) : ViewModel() {

    private val _dataUser = MutableLiveData<LoginSession>()
    val profile: LiveData<LoginSession>
        get() = _dataUser

    private val _loginStatus = SingleEventLiveData<Boolean>()
    val loginStatus: LiveData<Boolean>
        get() = _loginStatus

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error


    fun makeLogin(email: String, password: String){

        val login = UserLogin(email, password)

        viewModelScope.launch(Dispatchers.IO) {
                loginRepository.fetchLogin(login)
                    .onStart { _progressBar.postValue(true) }
                    .catch { exception ->
                        if(exception is HttpException){
                            var errorType: String?
                            val code = exception.code()
                            exception.response()?.errorBody()?.string()?.let {
                                val apiError = Gson().fromJson(it, ApiError::class.java)
                                errorType = apiError.type
                               _loginStatus.postValue(false)
                            }
                        }
                    }
                    .onCompletion { _progressBar.postValue(false) }
                    .collect {
                        val result = it
                        _dataUser.postValue(result)
                        _loginStatus.postValue(true)

                        UserSettings.token = result.token
                        UserSettings.user = result.user

                    }
        }
    }

}