package com.example.amazondelievery.ui.launch.fragment.home.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.amazondelievery.BuildConfig
import com.example.amazondelievery.R
import com.example.amazondelievery.data.viewmodel.DashboardViewModel
import com.example.amazondelievery.di.blockUI
import com.example.amazondelievery.di.setCircularView
import com.example.amazondelievery.di.toast
import com.example.amazondelievery.di.unBlockUI
import com.example.amazondelievery.ui.launch.MainActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.layout_bank_and_bike.*
import kotlinx.android.synthetic.main.layout_blood_and_insurance.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(), BackPressEvent {

    private val vmDashboardViewModel by viewModel<DashboardViewModel>()

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

    }

    private fun getEmployeeDetail() {
        pbDashboard.blockUI(activity as AppCompatActivity)
        vmDashboardViewModel.getEmployeeDetail(onSuccess = { employeeDetail ->
            employeeDetail.content?.let {
                tvName.text = it.employee_name
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
        toDelivery = true
    }

    override fun backTriggered(): Boolean {
        if (toDelivery) {
            ilDelivery.visibility = View.GONE
            group1.visibility = View.VISIBLE
            toDelivery = false
            return false
        }
        return true
    }

}
