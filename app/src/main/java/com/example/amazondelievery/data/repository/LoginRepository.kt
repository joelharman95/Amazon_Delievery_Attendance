package com.example.amazondelievery.data.repository

import com.example.amazondelievery.data.api.request.ReqAllDetails
import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.api.response.ResponseLogin
import com.example.amazondelievery.data.api.service.AuthenticationApi
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(
    private val api: AuthenticationApi
) : ILoginRepository {

    override suspend fun login(
        requestLogin: ReqLogin,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.login(requestLogin)
                println("GET_____$response")
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!) {
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        } else {
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                        }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

    override suspend fun postPersonalDetails(
        reqAllDetails: ReqAllDetails,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.postPersonalDetails(reqAllDetails)
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!) {
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        } else {
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                        }
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


interface ILoginRepository {

    suspend fun login(
        requestLogin: ReqLogin,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    )

    suspend fun postPersonalDetails(
        reqAllDetails: ReqAllDetails,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    )

    companion object Factory {
        fun getInstance(api: AuthenticationApi): ILoginRepository = LoginRepository(api)
    }

}