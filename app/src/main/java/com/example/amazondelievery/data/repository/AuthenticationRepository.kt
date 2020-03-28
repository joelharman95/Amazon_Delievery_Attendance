package com.example.amazondelievery.data.repository

import android.util.Log
import com.example.amazondelievery.data.api.request.ReqAllDetails
import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.api.response.ResponseLogin
import com.example.amazondelievery.data.api.response.ResponseProfileStatus
import com.example.amazondelievery.data.api.service.AuthenticationApi
import com.example.amazondelievery.di.utility.ImageConstants
import com.example.amazondelievery.di.utility.ImageConstants.INSURANCE
import com.example.amazondelievery.di.utility.ImageConstants.LICENSE
import com.example.amazondelievery.di.utility.ImageConstants.PROFILE_PIC
import com.example.amazondelievery.di.utility.ImageConstants.RCBOOK
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class  AuthenticationRepository(
    private val api: AuthenticationApi
) : IAuthenticationRepository {

    override suspend fun login(
        requestLogin: ReqLogin,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.login(requestLogin)
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

    override suspend fun employeeProfileStatus(
        onSuccess: OnSuccess<ResponseProfileStatus>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.employeeProfileStatus()
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

    override suspend fun postPersonalDetails(
        reqAllDetails: ReqAllDetails,
        files: HashMap<String, String>,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val contactDetails = RequestBody.create(MediaType.parse("multipart/form-data"), Gson().toJson(reqAllDetails))
              //  val contactDetails = RequestBody.create(MediaType.parse("text/plain"), Gson().toJson(reqAllDetails))    //  Both are working

                val file1 = File(files[PROFILE_PIC].toString())
                val file2 = File(files[LICENSE].toString())
                val file3 = File(files[INSURANCE].toString())
                val file4 = File(files[RCBOOK].toString())

                val requestProFile = RequestBody.create(MediaType.parse("image/*"), file1)
                val requestLicenseFile = RequestBody.create(MediaType.parse("image/*"), file2)
                val requestInsuranceFile = RequestBody.create(MediaType.parse("image/*"), file3)
                val requestLcBookFile = RequestBody.create(MediaType.parse("image/*"), file4)

                val proFile = MultipartBody.Part.createFormData("proFile", file1.name, requestProFile)
                val licenseFile = MultipartBody.Part.createFormData("licenseFile", file2.name, requestLicenseFile)
                val insuranceFile = MultipartBody.Part.createFormData("insuranceFile", file3.name, requestInsuranceFile)
                val rcBookFile = MultipartBody.Part.createFormData("rcBookFile", file4.name, requestLcBookFile)

                val response = api.postPersonalDetails(contactDetails, proFile, licenseFile, insuranceFile, rcBookFile)
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!) {
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        } else {
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                        }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("Tag", e.toString())
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

    override suspend fun checkLogin(
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.checkLogin()
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

}


interface IAuthenticationRepository {

    suspend fun login(
        requestLogin: ReqLogin,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    )

    suspend fun employeeProfileStatus(
        onSuccess: OnSuccess<ResponseProfileStatus>,
        onError: OnError<String>
    )

    suspend fun postPersonalDetails(
        reqAllDetails: ReqAllDetails,
        files: HashMap<String, String>,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    )

    suspend fun checkLogin(
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    )

    companion object Factory {
        fun getInstance(api: AuthenticationApi): IAuthenticationRepository =  AuthenticationRepository(api)
    }

}