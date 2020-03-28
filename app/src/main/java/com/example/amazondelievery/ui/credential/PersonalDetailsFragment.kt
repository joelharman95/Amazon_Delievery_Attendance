package com.example.amazondelievery.ui.credential

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.request.ReqAllDetails
import com.example.amazondelievery.di.initToolbar
import com.example.amazondelievery.di.utility.Constants.PERSONAL_DETAILS
import kotlinx.android.synthetic.main.fragment_personal_details.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.regex.Pattern

class PersonalDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.initToolbar(tbar, PERSONAL_DETAILS, findNavController())

        btnNext.setOnClickListener {
            if (isFieldValid()) {
                val reqAllDetails = ReqAllDetails(
                    altMobileNumber = etPhoneNo.text.toString(),
                    permanentAddress = etPermanentAddr.text.toString(),
                    temporaryAddress = etTemporaryAddr.text.toString(),
                    bloodGroup = etBloodGroup.text.toString(),
                    accountNumber = etAccountNo.text.toString(),
                    bankName = etBankName.text.toString(),
                    ifscCode = etIfscCode.text.toString(),
                    branch = etBranch.text.toString()
                )
                when (findNavController().currentDestination?.id) {
                    R.id.personalDetailsFrag -> findNavController().navigate(
                        R.id.action_details_to_vehicle,
                        bundleOf(PERSONAL_DETAILS to reqAllDetails)
                    )
                }
            }
        }
    }

    private fun isFieldValid(): Boolean {
        val ifscRegex = "^[A-Za-z]{4}[a-zA-Z0-9]{7}$"
        val pattern = Pattern.compile(ifscRegex)
      //  var reg: Regex = "/^[A-Za-z]{4}0[A-Z0-9]{6}$/"
        return when {
            TextUtils.isEmpty(etPhoneNo.text.toString()) -> {
                etPhoneNo.error = "Phone number required"
                false
            }
            TextUtils.isEmpty(etPermanentAddr.text.toString()) -> {
                etPermanentAddr.error = "Permanent address required"
                false
            }
            TextUtils.isEmpty(etTemporaryAddr.text.toString()) -> {
                etTemporaryAddr.error = "Temporary address required"
                false
            }
            TextUtils.isEmpty(etBloodGroup.text.toString()) -> {
                etBloodGroup.error = "Blood group required"
                false
            }
            TextUtils.isEmpty(etAccountNo.text.toString()) -> {
                etAccountNo.error = "Account number required"
                false
            }
            etAccountNo.text.toString().length in 19 downTo 8 -> {
                etAccountNo.error = "Valid account number required"
                false
            }
            TextUtils.isEmpty(etBankName.text.toString()) -> {
                etBankName.error = "Bank name required"
                false
            }
            TextUtils.isEmpty(etIfscCode.text.toString()) -> {
                etIfscCode.error = "Ifsc code required"
                false
            }
            !pattern.matcher(etIfscCode.text.toString()).matches() -> {
                etIfscCode.error = "Valid Ifsc code required"
                false
            }
            TextUtils.isEmpty(etBranch.text.toString()) -> {
                etBranch.error = "Branch required"
                false
            }
            else -> true
        }
    }

}
