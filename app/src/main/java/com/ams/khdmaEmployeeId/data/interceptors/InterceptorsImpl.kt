package com.ams.khdmaEmployeeId.data.interceptors

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Factory

@Factory
class InterceptorsImpl(private val application: Application): HttpInterceptors {

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            redactHeader("Authorization")
            redactHeader("Bearer")
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    private val chuckerInterceptor by lazy {
        ChuckerInterceptor.Builder(application)
            .redactHeaders("Authorization", "Bearer")
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .alwaysReadResponseBody(true)
            .build()
    }

    override fun getInterceptors() = listOf(loggingInterceptor, chuckerInterceptor)
}