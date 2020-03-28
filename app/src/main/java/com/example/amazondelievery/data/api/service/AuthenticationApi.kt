package com.example.amazondelievery.data.api.service

import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.api.response.ResponseLogin
import com.example.amazondelievery.data.api.response.ResponseProfileStatus
import com.example.amazondelievery.di.utility.ApiUrls.CHECK_LOGIN
import com.example.amazondelievery.di.utility.ApiUrls.EMPLOYEE_PROFILE_STATUS
import com.example.amazondelievery.di.utility.ApiUrls.LOGIN_URL
import com.example.amazondelievery.di.utility.ApiUrls.UPDATE_PROFILE_DETAILS
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AuthenticationApi {

    @GET(CHECK_LOGIN)
    suspend fun checkLogin(
    ): Response<ResponseLogin>

    @POST(LOGIN_URL)
    suspend fun login(
        @Body requestLogin: ReqLogin
    ): Response<ResponseLogin>

    @Multipart
    @POST(UPDATE_PROFILE_DETAILS)
    suspend fun postPersonalDetails(
        @Part("contactDetails") contactDetails: RequestBody,
        @Part proFile: MultipartBody.Part,
        @Part licenseFile: MultipartBody.Part,
        @Part insuranceFileName: MultipartBody.Part,
        @Part rcBookFile: MultipartBody.Part
    ): Response<ResponseLogin>

    @GET(EMPLOYEE_PROFILE_STATUS)
    suspend fun employeeProfileStatus() : Response<ResponseProfileStatus>
}