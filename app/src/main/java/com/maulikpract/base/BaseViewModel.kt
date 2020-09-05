package com.maulikpract.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maulikpract.api.ApiService
import kotlinx.coroutines.cancel
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel : ViewModel(),KoinComponent {

    protected val apiService: ApiService by inject()

    abstract fun provieState() : BaseState

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}