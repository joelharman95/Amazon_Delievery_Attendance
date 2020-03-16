package com.example.amazondelievery.data.repository

import com.example.amazondelievery.data.api.response.ResponseProfileStatus
import com.example.amazondelievery.data.api.service.AuthenticationApi
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SplashRepository(private val api: AuthenticationApi) : ISplashRepository {

    override suspend fun validateToken(
        token: String,
        onSuccess: OnSuccess<ResponseProfileStatus>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.validateToken(token)
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

interface ISplashRepository {

    suspend fun validateToken(
        token: String,
        onSuccess: OnSuccess<ResponseProfileStatus>,
        onError: OnError<String>
    )

    companion object Factory {
        fun getInstance(api: AuthenticationApi): ISplashRepository = SplashRepository(api)
    }

}
