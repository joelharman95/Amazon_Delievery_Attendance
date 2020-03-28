package com.example.amazondelievery.data.api.service

import com.example.amazondelievery.data.api.request.RequestLeave
import com.example.amazondelievery.data.api.response.*
import com.example.amazondelievery.di.utility.ApiUrls
import com.example.amazondelievery.di.utility.ApiUrls.APPROVED_LEAVE_MANAGEMENT
import com.example.amazondelievery.di.utility.ApiUrls.CREATE_LEAVE_REQUEST
import com.example.amazondelievery.di.utility.ApiUrls.EMPLOYEE_DETAILS_FOR_MOBILE
import com.example.amazondelievery.di.utility.ApiUrls.EMPLOYEE_FOR_DEL_ASSOCIATE
import com.example.amazondelievery.di.utility.ApiUrls.LATEST_LEAVE_DETAIL
import com.example.amazondelievery.di.utility.ApiUrls.LEAVE_LIST
import com.example.amazondelievery.di.utility.ApiUrls.LEAVE_MANAGEMENT_LIST
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeApi {

    @GET(EMPLOYEE_DETAILS_FOR_MOBILE)
    suspend fun getEmployeeDetail(): Response<ResponseEmployeeDetails>

    @POST(CREATE_LEAVE_REQUEST)
    suspend fun sendLeaveRequest(
        @Body requestLeave: RequestLeave
    ): Response<ResponseLogin>

    @GET(LEAVE_LIST)
    suspend fun getLeaveList(
        @Query("size") size: Int,
        @Query("page") page: Int
    ): Response<ResponseLeaveList>

    @GET(LEAVE_MANAGEMENT_LIST)
    suspend fun getSupervisorLeaveList(
        @Query("size") size: Int,
        @Query("page") page: Int
    ): Response<ResponseLeaveList>

    @POST(APPROVED_LEAVE_MANAGEMENT)
    suspend fun postLeaveManagement(
        @Query("status") status: Int,
        @Query("lm_id") lmId: Int
    ): Response<ResponseLeaveApproval>

    @GET(EMPLOYEE_FOR_DEL_ASSOCIATE)
    suspend fun getDelAssociateteList(
        @Query("size") size: Int,
        @Query("page") page: Int
    ): Response<ResponseDelAssociatete>

    @GET(LATEST_LEAVE_DETAIL)
    suspend fun getLatestLeaveList(
        @Query("size") size: Int,
        @Query("page") page: Int
    ): Response<ResponseLatestLeaveList>
}