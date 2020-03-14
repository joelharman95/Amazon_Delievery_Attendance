package com.example.amazondelievery.data.api.service

import com.example.amazondelievery.data.api.response.ResponseEmployeeDetails
import com.example.amazondelievery.data.api.response.ResponseLogin
import com.example.amazondelievery.di.utility.LoginUrls.EMPLOYEE_DETAILS_FOR_MOBILE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET(EMPLOYEE_DETAILS_FOR_MOBILE)
    suspend fun getEmployeeDetail(): Response<ResponseEmployeeDetails>
}