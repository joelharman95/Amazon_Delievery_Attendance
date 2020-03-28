package com.example.amazondelievery.di.utility

object Constants {
    const val PERSONAL_DETAILS = "PersonalDetails"
    const val VEHICLE_DETAILS = "VehicleDetails"
    const val PHOTO_PROOFS = "PhotoProofs"
}

object Pref {
    const val AMAZON_SHARED_PREFERENCES = "amazon_shared_pref"
    const val TOKEN = "Access-token"
    const val VERIFY_STATUS = "verify_status"
    const val IS_LOGGED_IN = "isloggedin"
}

object ApiUrls {
    const val LOGIN_URL = "login"
    const val UPDATE_PROFILE_DETAILS = "updateProfileDetails"
    const val CHECK_LOGIN = "loginCheck"
    const val EMPLOYEE_PROFILE_STATUS = "employeeProfileStatus"
    const val EMPLOYEE_DETAILS_FOR_MOBILE = "employeeDetailsForMobile"
    const val CREATE_LEAVE_REQUEST = "createLeaveRequest"
    const val LEAVE_LIST = "leaveListPagination"
    const val LEAVE_MANAGEMENT_LIST = "supervisorLeaveManagementList"
    const val LATEST_LEAVE_DETAIL = "latustLeaveDetails"
    const val EMPLOYEE_FOR_DEL_ASSOCIATE = "listPageEmployeeForDelAssociate"
    const val APPROVED_LEAVE_MANAGEMENT = "approvedLeaveManagement"
}

object ImageConstants{
    const val GALLERY = 1
    const val CAMERA = 2
    const val IMAGE_DIRECTORY = "Proofs"
    const val PROFILE_PIC = "ProfilePic"
    const val LICENSE = "License"
    const val INSURANCE = "Insurance"
    const val RCBOOK = "RcBook"
}

object TaskConstant {
    const val DAY = 1
    const val TIME = 2
    const val CUSTOM = 3
}