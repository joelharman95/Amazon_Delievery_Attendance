package com.example.amazondelievery.di.utility

import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

inline fun <T> Response<T>.unwrap(
    successCode: Int = HttpsURLConnection.HTTP_OK,
    block: (result: T?) -> Unit = {}
): T = if (this.isSuccessful && this.code() == successCode) {
    val data = body() ?: throw IllegalStateException("Body is null")
    block(data)
    data
} else {
    throw Exception(errorBody()?.string() ?: "An unexpected error has occurred")
}