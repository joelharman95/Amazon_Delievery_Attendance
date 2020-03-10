package com.example.amazondelievery.data.api.service

import com.example.amazondelievery.data.api.request.ReqAllDetails
import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.api.response.ResponseLogin
import com.example.amazondelievery.di.utility.LoginUrls.LOGIN_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {

    @POST(LOGIN_URL)
    suspend fun login(
        @Body requestLogin: ReqLogin
    ): Response<ResponseLogin>

    @POST(LOGIN_URL)
    suspend fun postPersonalDetails(
        @Body reqAllDetails: ReqAllDetails
    ): Response<ResponseLogin>
}