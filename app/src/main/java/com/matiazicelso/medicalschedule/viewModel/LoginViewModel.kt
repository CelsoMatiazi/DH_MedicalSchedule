package com.matiazicelso.medicalschedule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.gson.Gson
import com.matiazicelso.medicalschedule.data.model.*
import com.matiazicelso.medicalschedule.data.repository.LoginRepository
import com.matiazicelso.medicalschedule.utils.FacebookCustomCallback
import com.matiazicelso.medicalschedule.utils.GoogleLogInActivityContract
import com.matiazicelso.medicalschedule.utils.SingleEventLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val loginRepository: LoginRepository = LoginRepository.instance,
) : ViewModel() {

    val callbackManager = CallbackManager.Factory.create()
    val loginManager : LoginManager = LoginManager.getInstance()

    private val _dataUser = MutableLiveData<LoginSession>()
    val dataUser: LiveData<LoginSession>
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

    private val _socialLoginError = MutableLiveData<String>()
    val socialLoginError: LiveData<String>
        get() = _socialLoginError



    init {
        loginManager.registerCallback(callbackManager, FacebookCustomCallback {
            when(it){
                is FacebookCustomCallback.Result.Success -> {
                    socialLogin(FACEBOOK_ARGS, it.token)
                }
                else -> {
                    _socialLoginError.value = FACEBOOK_ARGS
                }
            }
        })
    }


    fun makeLogin(email: String, password: String) = viewModelScope.launch {
        val login = UserLogin(email, password)
        loginRepository.fetchLogin(login)
            .onStart { _progressBar.postValue(true) }
            .catch { _loginStatus.postValue(false) }
            .onCompletion { _progressBar.postValue(false) }
            .collect {
                val result = it
                _dataUser.postValue(result)
                _loginStatus.postValue(true)
                UserSettings.token = result.token
                UserSettings.user = result.user
            }
    }



    private fun socialLogin(type: String, token: String) = viewModelScope.launch {
        loginRepository.socialLogin(SocialLogin(type, token)).collect {
            _dataUser.value = it
            _loginStatus.value = true
            UserSettings.token = it.token
            UserSettings.user = it.user
        }
    }


    fun loginGoogle(result: GoogleLogInActivityContract.Result) {
        when(result) {
            is GoogleLogInActivityContract.Result.Success -> {
                socialLogin(GOOGLE_ARGS, result.googleSignInAccount.idToken.toString())
            }

            is GoogleLogInActivityContract.Result.Error -> {
                _socialLoginError.value = GOOGLE_ARGS
            }
        }
    }

    companion object {
        const val FACEBOOK_ARGS = "FACEBOOK"
        const val GOOGLE_ARGS = "GOOGLE"
    }


}