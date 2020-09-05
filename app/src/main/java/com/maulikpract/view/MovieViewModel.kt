package com.maulikpract.view

import androidx.lifecycle.viewModelScope
import com.maulikpract.base.BaseState
import com.maulikpract.base.BaseViewModel
import kotlinx.coroutines.launch

class MovieViewModel(private val state : MovieState) : BaseViewModel() {
    override fun provieState(): BaseState = state

    fun fetchMovieData(){
        state.isLoading.value = true
        state.movieApiState.postValue(MovieState.MovieApiState.Loading)
        viewModelScope.launch {
            kotlin.runCatching {
                apiService.getMovieList()
            }.onSuccess {
                state.isLoading.value = false
                it?.let {
                    state.movieApiState.postValue(MovieState.MovieApiState.Success(it))
                }
            }.onFailure {
                state.isLoading.value = false
                state.movieApiState.postValue(MovieState.MovieApiState.Error(it))
            }
        }
    }

}