package com.example.amazondelievery.ui.home.dashboard

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.BuildConfig
import com.example.amazondelievery.R
import com.example.amazondelievery.data.viewmodel.DashboardViewModel
import com.example.amazondelievery.di.*
import com.example.amazondelievery.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.layout_bank_and_bike.*
import kotlinx.android.synthetic.main.layout_blood_and_insurance.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.math.roundToInt

class DashboardFragment : Fragment(), BackPressEvent {

    private val vmDashboard by viewModel<DashboardViewModel>()

    private var toDelivery = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.setCircularView(imageView = ivProfilePic, pic = R.drawable.icon_user)
        activity?.setCircularView(imageView = ivTakePic, pic = R.drawable.icon_camera)

        (activity as MainActivity).setListener(this)
        getEmployeeDetail()

        llCompleteDelivery.setOnClickListener {
            showDelivery(isCompleteDelivery = true)
        }

        llTotalDelivery.setOnClickListener {
            showDelivery(false)
        }

        btnLogout.setOnClickListener {
            vmDashboard.clearToken()
            when(findNavController().currentDestination?.id) {
                R.id.dashboardFrag -> findNavController().navigate(R.id.action_dashboardFrag_to_loginFragment)
            }
        }

    }

    private fun getEmployeeDetail() {
        pbDashboard.blockUI(activity as AppCompatActivity)
        vmDashboard.getEmployeeDetail(onSuccess = { employeeDetail ->
            employeeDetail.content?.let {
              //  tvName.text = it.employee_name
                tvName.applySpanPo(it.employee_name.toString(), "(Present)", R.color.colorAccent)
                tvPhoneNo.text = it.mobile_number
                tvAddress.text = it.permanent_address
                tvAccountNo.text = it.account_number
                tvBankName.text = it.bank_name
                tvBranch.text = it.branch
                tvIFSCCode.text = it.ifsc_code
                tvBikeNo.text = it.bike_number
                tvBikeModel.text = it.bike_model
                tvBloodGroup.text = it.blood_group
                tvInsuranceNo.text = it.bike_insurance_number
                tvInsuranceExpDate.text = it.bike_insurance_exp_date
                activity?.setCircularView(ivProfilePic, BuildConfig.BASE_URL + it.proFileLocation)
            }
            pbDashboard.unBlockUI(activity as AppCompatActivity)
        }, onError = {
            activity?.toast(it)
            pbDashboard.unBlockUI(activity as AppCompatActivity)
        })
    }

    private fun showDelivery(isCompleteDelivery: Boolean) {
        ilDelivery.visibility = View.VISIBLE
        group1.visibility = View.GONE
        groupButton.visibility = View.GONE
        toDelivery = true
    }

    fun calculateDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Float {
        val results = FloatArray(1)
        Location.distanceBetween(lat1, lng1, lat2, lng2, results)
        // distance in meter

      //  if (calculateDistance() <= 5)   //  to use

        return results[0]
    }

  //  val d = latLng1.distanceTo(latLng2)

    fun distanceText(distance: Float): String {
        return if (distance < 1000)
            if (distance < 1)
                String.format(Locale.US, "%dm", 1)
            else
                String.format(Locale.US, "%dm", distance.roundToInt())
        else if (distance > 10000)
            if (distance < 1000000)
                String.format(Locale.US, "%dkm", (distance / 1000).roundToInt())
            else
                "FAR"
        else
            String.format(Locale.US, "%.2fkm", distance / 1000)
    }

    override fun backTriggered(): Boolean {
        if (toDelivery) {
            ilDelivery.visibility = View.GONE
            group1.visibility = View.VISIBLE
            groupButton.visibility = View.VISIBLE
            toDelivery = false
            return false
        }
        return true
    }

}
