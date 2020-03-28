package com.example.amazondelievery.data.api.response

import com.google.gson.annotations.SerializedName

data class ResponseDelAssociatete(
    val message: String? = null,
    @SerializedName("iserror") val isError: Boolean? = null,
    val content: DelAssociateteLeave? = null
)

data class DelAssociateteLeave(
    @SerializedName("employees_detail_list") val employeeDelAssociateList: List<EmployeeDelAssociate?>? = null,
    val page: Page? = null
)

data class EmployeeDelAssociate(
    @SerializedName("employee_id") val employeeId : Int? = null,
    @SerializedName("employee_name") val employeeName : String? = null,
    @SerializedName("password") val password : String? = null,
    @SerializedName("mobile_number") val mobileNumber : String? = null,
    @SerializedName("alt_mobile_number") val altMobileNumber : String? = null,
    @SerializedName("blood_group") val blood_group : String? = null,
    @SerializedName("temporary_address") val temporary_address : String? = null,
    @SerializedName("permanent_address") val permanent_address : String? = null,
    @SerializedName("account_number") val account_number : String? = null,
    @SerializedName("bank_name") val bank_name : String? = null,
    @SerializedName("ifsc_code") val ifsc_code : String? = null,
    @SerializedName("branch") val branch : String? = null,
    @SerializedName("bike_number") val bikeNumber : String? = null,
    @SerializedName("bike_model") val bikeModel : String? = null,
    @SerializedName("bike_insurance_number") val bike_insurance_number : String? = null,
    @SerializedName("bike_insurance_exp_date") val bikeInsuranceExpDate : String? = null,
    @SerializedName("license_number") val license_number : String? = null,
    @SerializedName("rc_book_number") val rc_book_number : String? = null,
    @SerializedName("active_status") val active_status : Int? = null,
    @SerializedName("role_id") val role_id : Int? = null,
    @SerializedName("role_name") val role_name : String? = null,
    @SerializedName("pro_file_pic") val proFilePic : String? = null,
    @SerializedName("application") val application : String? = null,
    @SerializedName("associate_type") val associateType : String? = null,
    @SerializedName("office_id") val office_id : String? = null,
    @SerializedName("office_name") val office_name : String? = null,
    @SerializedName("approved_status") val approved_status : Int? = null,
    @SerializedName("proFileLocation") val proFileLocation : String? = null,
    @SerializedName("approved") val approved : Approved? = null,
    @SerializedName("created") val created : Created? = null
)

data class Approved(
    @SerializedName("approved_by_id") val approved_by_id : Int? = null,
    @SerializedName("approved_by_name") val approved_by_name : String? = null,
    @SerializedName("approved_by_role_name") val approved_by_role_name : String? = null
)

data class Created (
    @SerializedName("created_by_id") val createdById : Int? = null,
    @SerializedName("created_by_name") val createdByName : String? = null,
    @SerializedName("created_by_role_name") val createdByRoleName : String? = null,
    @SerializedName("created_time") val createdTime : String? = null
)