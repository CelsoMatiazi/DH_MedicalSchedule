package com.matiazicelso.medicalschedule.data.netWork

sealed class DataResult<out T : Any>{
    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val error: Throwable) : DataResult<Nothing>()
    data class Loading(val isLoading: Boolean) : DataResult<Nothing>()
    object Empty: DataResult<Nothing>()
}
