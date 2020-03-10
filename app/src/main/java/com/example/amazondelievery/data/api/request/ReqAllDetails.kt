package com.example.amazondelievery.data.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReqAllDetails(

    //  Personal Detail Fields
    @field:SerializedName("alt_mobile_number") val altMobileNumber: String? = null,
    @field:SerializedName("permanent_address") val permanentAddress: String? = null,
    @field:SerializedName("temporary_address") val temporaryAddress: String? = null,
    @field:SerializedName("blood_group") val bloodGroup: String? = null,
    @field:SerializedName("account_number") val accountNumber: String? = null,
    @field:SerializedName("bank_name") val bankName: String? = null,
    @field:SerializedName("ifsc_code") val ifscCode: String? = null,
    @field:SerializedName("branch") val branch: String? = null,

    //  Vehicle Detail Fields
    @field:SerializedName("bike_model") var bikeModel: String? = null,
    @field:SerializedName("bike_number") var bikeNumber: String? = null,
    @field:SerializedName("license_number") var licenseNumber: String? = null,
    @field:SerializedName("bike_insurance_number") var bikeInsuranceNumber: String? = null,
    @field:SerializedName("bike_insurance_exp_date") var bikeInsuranceExpDate: String? = null,
    @field:SerializedName("rc_book_number") var rcBookNumber: String? = null,

    //  Can use it for future purpose
    @field:SerializedName("employee_name") val employeeName: String? = null,
    @field:SerializedName("password") val password: String? = null,
    @field:SerializedName("active_status") val activeStatus: Boolean? = null,
    @field:SerializedName("role_id") val roleId: Int? = null,
    @field:SerializedName("employee_id") val employeeId: Int? = null,
    @field:SerializedName("mobile_number") val mobileNumber: String? = null
) : Parcelable