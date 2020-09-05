package com.maulikpract.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.maulikpract.api.ApiService
import com.maulikpract.utils.BASE_URL
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val HTTP_LOGGING_INTERCEPTOR: String = "HTTP_LOGGING_INTERCEPTOR"
private const val OKHTTP_CLIENT = "OKHTTP_CLIENT"
private const val CACHE_SIZE: Long = 10 * 1024 * 1024 // 10 MB
private const val NETWORK_CALL_TIMEOUT: Long = 1 // Minute

val networkModule = module {
    single<Interceptor>(named(HTTP_LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
    }

    factory<OkHttpClient>(named(OKHTTP_CLIENT)) {
        val cacheDir = androidContext().cacheDir

        OkHttpClient.Builder()
            .cache(Cache(cacheDir, CACHE_SIZE))
            .addInterceptor(get<Interceptor>(named(HTTP_LOGGING_INTERCEPTOR)))
            .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.MINUTES)
            .build()
    }

    factory {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get(named(OKHTTP_CLIENT)))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }
}