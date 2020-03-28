package com.example.amazondelievery.data.api.request

data class RequestLeave(
    val type: String? = null,
    val from_date: String? = null,  //  "2020-03-16"
    val to_date: String? = null,
    val leave_request: String? = null
)