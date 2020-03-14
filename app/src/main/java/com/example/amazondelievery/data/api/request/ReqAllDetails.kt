package com.example.amazondelievery.data.api.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReqAllDetails(

    //  Personal Detail Fields
    @SerializedName("alt_mobile_number") val altMobileNumber: String? = null,
    @SerializedName("permanent_address") val permanentAddress: String? = null,
    @SerializedName("temporary_address") val temporaryAddress: String? = null,
    @SerializedName("blood_group") val bloodGroup: String? = null,
    @SerializedName("account_number") val accountNumber: String? = null,
    @SerializedName("bank_name") val bankName: String? = null,
    @SerializedName("ifsc_code") val ifscCode: String? = null,
    @SerializedName("branch") val branch: String? = null,

    //  Vehicle Detail Fields
    @SerializedName("bike_model") var bikeModel: String? = null,
    @SerializedName("bike_number") var bikeNumber: String? = null,
    @SerializedName("license_number") var licenseNumber: String? = null,
    @SerializedName("bike_insurance_number") var bikeInsuranceNumber: String? = null,
    @SerializedName("bike_insurance_exp_date") var bikeInsuranceExpDate: String? = null,
    @SerializedName("rc_book_number") var rcBookNumber: String? = null/*,

    //  Can use it for future purpose
    @SerializedName("employee_name") val employeeName: String? = null,
    @SerializedName("password") val password: String? = null,
    @SerializedName("active_status") val activeStatus: Boolean? = null,
    @SerializedName("role_id") val roleId: Int? = null,
    @SerializedName("employee_id") val employeeId: Int? = null,
    @SerializedName("mobile_number") val mobileNumber: String? = null*/
) : Parcelable