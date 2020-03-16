package com.example.amazondelievery.ui.launch.fragment.credential

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.request.ReqAllDetails
import com.example.amazondelievery.data.viewmodel.LoginViewModel
import com.example.amazondelievery.di.initToolbar
import com.example.amazondelievery.di.savePic
import com.example.amazondelievery.di.showDialogToPick
import com.example.amazondelievery.di.toast
import com.example.amazondelievery.di.utility.Constants
import com.example.amazondelievery.di.utility.ImageConstants.CAMERA
import com.example.amazondelievery.di.utility.ImageConstants.GALLERY
import com.example.amazondelievery.di.utility.ImageConstants.INSURANCE
import com.example.amazondelievery.di.utility.ImageConstants.LICENSE
import com.example.amazondelievery.di.utility.ImageConstants.PROFILE_PIC
import com.example.amazondelievery.di.utility.ImageConstants.RCBOOK
import kotlinx.android.synthetic.main.fragment_photo_proofs.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoProofsFragment : Fragment(), View.OnClickListener {

    private val vmLogin by viewModel<LoginViewModel>()
    private var fileNo = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_proofs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.initToolbar(tbar, Constants.PHOTO_PROOFS, findNavController())
        initClickListener()

    }

    private fun initClickListener() {
        cvProfilePic.setOnClickListener(this)
        cvLicense.setOnClickListener(this)
        cvInsurance.setOnClickListener(this)
        cvRCBook.setOnClickListener(this)
        btnComplete.setOnClickListener(this)
        cvProfilePic.tag = ""
        cvLicense.tag = ""
        cvInsurance.tag = ""
        cvRCBook.tag = ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            when (requestCode) {
                GALLERY -> {
                    try {
                        val uri = data.data
                        val bitmap = if (Build.VERSION.SDK_INT < 28) {
                            MediaStore.Images.Media.getBitmap(context?.contentResolver, uri)
                        } else {
                            val source =
                                ImageDecoder.createSource(context?.contentResolver!!, uri!!)
                            ImageDecoder.decodeBitmap(source)
                        }
                        putFile(bitmap)
                    } catch (e: Exception) {
                        context?.toast("Failed to load image")
                    }
                }
                CAMERA -> {
                    val bitmap = data.extras?.get("data") as Bitmap
                    putFile(bitmap)
                }
            }
        }
    }

    private fun putFile(bitmap: Bitmap) {
        when (fileNo) {
            PROFILE_PIC -> {
                cvProfilePic.tag = context?.savePic(bitmap, PROFILE_PIC).toString()
            }
            LICENSE -> {
                cvLicense.tag = context?.savePic(bitmap, LICENSE).toString()
            }
            INSURANCE -> {
                cvInsurance.tag = context?.savePic(bitmap, INSURANCE).toString()
            }
            RCBOOK -> {
                cvRCBook.tag = context?.savePic(bitmap, RCBOOK).toString()
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.cvProfilePic -> {
                this.showDialogToPick()
                fileNo = PROFILE_PIC
            }
            R.id.cvLicense -> {
                this.showDialogToPick()
                fileNo = LICENSE
            }
            R.id.cvInsurance -> {
                this.showDialogToPick()
                fileNo = INSURANCE
            }
            R.id.cvRCBook -> {
                this.showDialogToPick()
                fileNo = RCBOOK
            }
            R.id.btnComplete -> {
                when {
                    TextUtils.isEmpty(cvProfilePic.tag.toString()) -> activity?.toast("Need to upload profile picture")
                    TextUtils.isEmpty(cvLicense.tag.toString()) -> activity?.toast("Need to upload license")
                    TextUtils.isEmpty(cvInsurance.tag.toString()) -> activity?.toast("Need to upload insurance")
                    TextUtils.isEmpty(cvRCBook.tag.toString()) -> activity?.toast("Need to upload rc book")
                    else -> {
                        //   pbComplete.blockUI(activity as AppCompatActivity)
                        val files = HashMap<String, String>()
                        files[PROFILE_PIC] = cvProfilePic.tag.toString()
                        files[LICENSE] = cvLicense.tag.toString()
                        files[INSURANCE] = cvInsurance.tag.toString()
                        files[RCBOOK] = cvRCBook.tag.toString()
                        vmLogin.postPersonalDetails(reqAllDetails = arguments?.get(Constants.VEHICLE_DETAILS) as ReqAllDetails,
                            files = files, onSuccess = {
                                //  pbComplete.unBlockUI(this.requireActivity())
                                when (findNavController().currentDestination?.id) {
                                    R.id.photoProofsFrag -> findNavController().navigate(
                                        R.id.action_dp_to_validation,
                                        null,
                                        NavOptions.Builder().setPopUpTo(0, false).build()
                                    )

                                    /*R.id.photoProofsFrag -> findNavController().navigate(
                                        R.id.action_dp_to_validation*//*,       //  Incase if using activity
                                    null, null,
                                ActivityNavigator.Extras.Builder()
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    .build()*//*
                            )*/
                                }
                            }, onError = {
                                //   pbComplete.unBlockUI(this.requireActivity())
                                activity?.toast(it)
                            })
                    }
                }
            }
        }
    }

}
