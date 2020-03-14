package com.example.amazondelievery.data.api.response

import com.google.gson.annotations.SerializedName

data class ResponseProfileStatus(
    @SerializedName("iserror") val isError: Boolean? = null,
    val message: String? = null,
    val content: ProfileStatus? = null
)

data class ProfileStatus(
    @SerializedName("profile_updated_status") val profileUpdatedStatus : Boolean = false,
    @SerializedName("approved_status") val approvedStatus : Boolean? = null
)