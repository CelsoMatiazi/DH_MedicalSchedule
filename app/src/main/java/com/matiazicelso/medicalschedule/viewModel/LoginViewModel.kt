package com.matiazicelso.medicalschedule.viewModel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiazicelso.medicalschedule.data.repository.RequestApi
import com.matiazicelso.medicalschedule.utils.SingleEventLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val request = RequestApi()

//    private val _login = MutableLiveData<Boolean>()
//    val login: LiveData<Boolean>
//        get() = _login

    private val _login = SingleEventLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _login


    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar


    fun makeLogin(email: String, password: String){

        viewModelScope.launch {
            try {
                _progressBar.value = true
                 delay(2000)
                _login.value = request.login(email,password)
            }catch(ex: Exception){
                Log.e("ERROR", "Ocoreu um erro")
            }finally {
                _progressBar.value = false
            }
        }
    }

}