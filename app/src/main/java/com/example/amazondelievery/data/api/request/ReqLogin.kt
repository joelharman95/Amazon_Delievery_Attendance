package com.example.amazondelievery.data.api.request

import com.google.gson.annotations.SerializedName

data class ReqLogin(
    @SerializedName("mobile_number") val mobileNumber: String? = null,
    val password: String? = null
)