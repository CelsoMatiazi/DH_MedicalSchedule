package com.matiazicelso.medicalschedule.data.netWork

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.IOException

fun <T : Any> Flow<DataResult<T>>.upDateStatus() = this
    .retryWhen { cause, attempt ->
        if(cause is IOException && attempt < 5){
            delay(5000)
            true
        }else{
            false
        }
    }
    .catch { e -> emit(DataResult.Error(e)) }
    .onStart { emit(DataResult.Loading(true)) }
    .onCompletion { emit(DataResult.Loading(false)) }