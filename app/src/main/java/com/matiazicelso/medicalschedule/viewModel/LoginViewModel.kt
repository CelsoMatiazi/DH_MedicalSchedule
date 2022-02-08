package com.matiazicelso.medicalschedule.viewModel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matiazicelso.medicalschedule.data.repository.RequestApi

class LoginViewModel: ViewModel() {

    private val request = RequestApi()

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _login

    fun makeLogin(email: String, password: String){
        Handler(Looper.getMainLooper()).postDelayed({
            _login.value = request.login(email,password)
        }, 2000)
    }
}