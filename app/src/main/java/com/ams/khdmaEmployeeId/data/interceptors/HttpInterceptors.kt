package com.ams.khdmaEmployeeId.data.interceptors

import okhttp3.Interceptor

interface HttpInterceptors {
    fun getInterceptors(): List<Interceptor>
}