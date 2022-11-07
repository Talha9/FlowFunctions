package com.example.flowfunctions

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainViewModel : ViewModel() {

    private val getCounterValue= MutableLiveData<Int>()

    fun setCountDownValue(value:Int){
       getCounterValue.value=value

    }

    val countDownTimer = flow<Int> {

        val startingValue = getCounterValue.value
        var currentValue = startingValue
        if (startingValue != null) {
            emit(startingValue)
        }
        if (currentValue != null) {
            while (currentValue > 0) {
                delay(1000L)
                currentValue--
                emit(currentValue)
            }
        }

    }
}