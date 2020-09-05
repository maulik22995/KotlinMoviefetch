package com.maulikpract.di

import com.maulikpract.view.MovieState
import org.koin.dsl.module

val stateModule = module {
    factory {
        MovieState()
    }
}