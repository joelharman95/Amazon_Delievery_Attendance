package com.example.amazondelievery.ui.launch.fragment.credential

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
import com.example.amazondelievery.di.utility.Constants.VEHICLE_DETAILS
import kotlinx.android.synthetic.main.fragment_vehicle_details.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class VehicleDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vehicle_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.initToolbar(tbar, VEHICLE_DETAILS, findNavController())

        //  val reqAllDetails = arguments?.get(PERSONAL_DETAILS) as ReqAllDetails
        val reqAllDetails =
            ReqAllDetails()     //  For testing purpose, need to remove it and uncomment the above statement

        println("GET___BUNDLE____FROM____PERSONAL___$reqAllDetails")

        btnNext.setOnClickListener {

            findNavController().navigate(R.id.action_vehicle_to_dp)     //  For testing purpose, need to remove it

            if (isFieldValid()) {
                reqAllDetails.bikeModel = etBikeModel.text.toString()
                reqAllDetails.bikeNumber = etBikeNumber.text.toString()
                reqAllDetails.licenseNumber = etLisenceNumber.text.toString()
                reqAllDetails.bikeInsuranceNumber = etLisenceNumber.text.toString()
                reqAllDetails.bikeInsuranceExpDate = etInsuranceExpDate.text.toString()
                reqAllDetails.rcBookNumber = etRCBoolNo.text.toString()

                when (findNavController().currentDestination?.id) {
                    R.id.vehicleDetailsFrag -> findNavController().navigate(
                        R.id.action_vehicle_to_dp,
                        bundleOf(VEHICLE_DETAILS to reqAllDetails)
                    )
                }
            }
        }
    }

    private fun isFieldValid(): Boolean {
        return when {
            TextUtils.isEmpty(etBikeModel.text.toString()) -> {
                etBikeModel.error = "Bike model required"
                false
            }
            TextUtils.isEmpty(etBikeNumber.text.toString()) -> {
                etBikeNumber.error = "Bike number required"
                false
            }
            TextUtils.isEmpty(etLisenceNumber.text.toString()) -> {
                etLisenceNumber.error = "License number required"
                false
            }
            TextUtils.isEmpty(etInsuranceNumber.text.toString()) -> {
                etInsuranceNumber.error = "Insurance number required"
                false
            }
            TextUtils.isEmpty(etInsuranceExpDate.text.toString()) -> {
                etInsuranceExpDate.error = "Insurance exp date required"
                false
            }
            TextUtils.isEmpty(etRCBoolNo.text.toString()) -> {
                etRCBoolNo.error = "RC book number required"
                false
            }
            else -> true
        }
    }

}
