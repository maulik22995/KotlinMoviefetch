package com.maulikpract.view

import androidx.lifecycle.MutableLiveData
import com.maulikpract.base.BaseState
import com.maulikpract.model.MovieData

class MovieState : BaseState {
    var isLoading = MutableLiveData<Boolean>()
    var movieApiState = MutableLiveData<MovieApiState>()


    sealed class MovieApiState(){
        object Loading : MovieApiState()
        data class Success(val data : MovieData) : MovieApiState()
        data class Error(val th : Throwable) : MovieApiState()
    }
}