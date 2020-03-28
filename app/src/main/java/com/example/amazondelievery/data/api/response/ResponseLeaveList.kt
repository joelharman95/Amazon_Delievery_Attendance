package com.example.amazondelievery.data.api.response

import com.google.gson.annotations.SerializedName

data class ResponseLeaveList(
    val message: String? = null,
    @SerializedName("iserror") val isError: Boolean? = null,
    val content: Leave? = null
)

data class Leave(
    @SerializedName("leave_management") val leaveManagement: List<LeaveManagementItem?>? = null,
    val page: Page? = null
)

data class LeaveManagementItem(
    @SerializedName("bike_number") val bikeNumber: String? = null,
    @SerializedName("from_date") val fromDate: String? = null,
    @SerializedName("created_time") val createdTime: String? = null,
    @SerializedName("employee_name") val employeeName: String? = null,
    val type: String? = null,
    val cancel_status: Int? = null,
    @SerializedName("role_name") val roleName: String? = null,
    @SerializedName("approved_by") val approvedBy: ApprovedBy? = null,
    @SerializedName("to_date") val toDate: String? = null,
    @SerializedName("employee_id") val employeeId: Int? = null,
    @SerializedName("leave_request") val leaveRequest: String? = null,
    val id: Int? = null,
    @SerializedName("mobile_number") val mobileNumber: String? = null,
    val status: String? = null,
    @SerializedName("profile_link") val profileLink: String? = null,
    @SerializedName("insurance_exp_date") val insuranceExpDate: String? = null,
    @SerializedName("bike_model") val bikeModel: String? = null,
    @SerializedName("approved_status") val approvedStatus: String? = null
)

data class ApprovedBy(
    val name: Any? = null,
    val id: Int? = null,
    @SerializedName("mobile_number") val mobileNumber: Any? = null,
    @SerializedName("role_name") val roleName: Any? = null
)

data class Page(
    val size: Int? = null,
    val totalPages: Int? = null,
    val currentPage: Int? = null,
    val totalElements: Int? = null
)