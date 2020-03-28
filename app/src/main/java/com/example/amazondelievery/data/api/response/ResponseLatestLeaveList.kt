package com.example.amazondelievery.data.api.response

import com.google.gson.annotations.SerializedName

data class ResponseLatestLeaveList(
    val message: String? = null,
    @SerializedName("iserror") val isError: Boolean? = null,
    val content: LeaveManagementItem? = null
)