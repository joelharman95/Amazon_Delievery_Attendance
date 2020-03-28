package com.example.amazondelievery.data.repository

import com.example.amazondelievery.data.api.request.RequestLeave
import com.example.amazondelievery.data.api.response.*
import com.example.amazondelievery.data.api.service.HomeApi
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DashboardRepository(
    private val api: HomeApi
) : IDashboardRepository {

    override suspend fun getEmployeeDetail(
        onSuccess: OnSuccess<ResponseEmployeeDetails>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getEmployeeDetail()
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

    override suspend fun sendLeaveRequest(
        requestLeave: RequestLeave,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.sendLeaveRequest(requestLeave)
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

    override suspend fun getLeaveList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseLeaveList>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getLeaveList(size, page)
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

    override suspend fun getSupervisorLeaveList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseLeaveList>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getSupervisorLeaveList(size, page)
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                println("GET____ERROR_____" + e.toString())
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

    override suspend fun postLeaveManagement(
        status: Int,
        lmId: Int,
        onSuccess: OnSuccess<ResponseLeaveApproval>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.postLeaveManagement(status, lmId)
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                println("GET____ERROR_____" + e.toString())
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

    override suspend fun getDelAssociateteList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseDelAssociatete>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getDelAssociateteList(size, page)
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                println("GET____ERROR_____" + e.toString())
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

    override suspend fun getLatestLeaveList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseLatestLeaveList>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getLatestLeaveList(size, page)
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

}


interface IDashboardRepository {

    suspend fun getEmployeeDetail(
        onSuccess: OnSuccess<ResponseEmployeeDetails>,
        onError: OnError<String>
    )

    suspend fun sendLeaveRequest(
        requestLeave: RequestLeave,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    )

    suspend fun getLeaveList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseLeaveList>,
        onError: OnError<String>
    )

    suspend fun getSupervisorLeaveList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseLeaveList>,
        onError: OnError<String>
    )

    suspend fun postLeaveManagement(
        status: Int,
        lmId: Int,
        onSuccess: OnSuccess<ResponseLeaveApproval>,
        onError: OnError<String>
    )

    suspend fun getDelAssociateteList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseDelAssociatete>,
        onError: OnError<String>
    )

    suspend fun getLatestLeaveList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseLatestLeaveList>,
        onError: OnError<String>
    )

    companion object Factory {
        fun getInstance(api: HomeApi): IDashboardRepository = DashboardRepository(api)
    }

}