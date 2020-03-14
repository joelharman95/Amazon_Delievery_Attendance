package com.example.amazondelievery.data.api.response

import com.google.gson.annotations.SerializedName

data class ResponseEmployeeDetails(
    @SerializedName("iserror") val isError: Boolean? = null,
    val message: String? = null,
    val content: EmployeeDetails? = null
)

data class EmployeeDetails(
    val employee_id : Int? = null,
    val employee_name : String? = null,
    val mobile_number : String? = null,
    val blood_group : String? = null,
    val permanent_address : String? = null,
    val account_number : String? = null,
    val bank_name : String? = null,
    val branch : String? = null,
    val ifsc_code : String? = null,
    val bike_number : String? = null,
    val bike_model : String? = null,
    val bike_insurance_number : String? = null,
    val bike_insurance_exp_date : String? = null,
    val proFileLocation : String? = null
)