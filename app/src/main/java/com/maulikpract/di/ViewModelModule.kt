package com.maulikpract.di

import com.maulikpract.view.MovieViewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory {
        MovieViewModel(get())
    }
}