package com.example.weatherforecast.core.data.api

import com.example.weatherforecast.core.API_KEY
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()

        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter("key", API_KEY)
            .build()

        val newRequest = Request.Builder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(newRequest)
    }
}