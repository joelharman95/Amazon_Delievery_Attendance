package com.example.amazondelievery.data.api

/*
 * *
 *  Created by Nethaji, Kanmalai Technologies Pvt. Ltd on 24/02/2020 11:00 AM.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 24/02/2020 11:00 AM by Nethaji.
 * *
 */

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient : IApiClient {

    override var okHttpClient: OkHttpClient =
        OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build()

    override var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    inline fun <T> getApi(service: Class<T>?): T {
        return retrofit.create(service as Class<T>)
    }

}

interface IApiClient {
    var okHttpClient: OkHttpClient
    var retrofit: Retrofit
}