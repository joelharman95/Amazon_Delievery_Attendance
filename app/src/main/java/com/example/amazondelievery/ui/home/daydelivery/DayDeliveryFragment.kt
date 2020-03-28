package com.example.amazondelievery.ui.home.daydelivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.amazondelievery.BuildConfig
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.response.LeaveManagementItem
import com.example.amazondelievery.data.viewmodel.DashboardViewModel
import com.example.amazondelievery.di.*
import com.example.amazondelievery.di.utility.PaginationListener
import kotlinx.android.synthetic.main.fragment_day_delivery.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayDeliveryFragment : Fragment() {

    private val vmDashboard by viewModel<DashboardViewModel>()
    private var currentPage = 0
    private var isLastPage = false
    private val PAGE_COUNT = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_day_delivery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.setCircularView(imageView = ivTakePic, pic = R.drawable.icon_camera)
        rvDayDelivery.adapter = DayDeliveryAdapter()
        getEmployeeDetail()

        pbDayDelivery.blockUI(activity as AppCompatActivity)
        vmDashboard.getSupervisorLeaveList(PAGE_COUNT, currentPage, {
            (rvDayDelivery.adapter as DayDeliveryAdapter).addDeliveryList(it.content?.leaveManagement as List<LeaveManagementItem>)
            if (!it.content.leaveManagement.isNullOrEmpty())
                tvNoDelivery.visibility = View.GONE
            else
                tvNoDelivery.visibility = View.VISIBLE
            pbDayDelivery.unBlockUI(activity as AppCompatActivity)
        }, {
            activity?.toast(it)
            pbDayDelivery.unBlockUI(activity as AppCompatActivity)
        })

        rvDayDelivery.addOnScrollListener(object : PaginationListener() {
            override fun loadMore() {
                currentPage++
                doApiCall()
            }
        })

    }

    private fun doApiCall() {
        if (!isLastPage) {
            pbDayDelivery.blockUI(activity as AppCompatActivity)
            vmDashboard.getSupervisorLeaveList(PAGE_COUNT, currentPage, {
                (rvDayDelivery.adapter as DayDeliveryAdapter).addDeliveryList(it.content?.leaveManagement as List<LeaveManagementItem>)
                pbDayDelivery.unBlockUI(activity as AppCompatActivity)
            }, {
                isLastPage = true
                activity?.toast(it)
                pbDayDelivery.unBlockUI(activity as AppCompatActivity)
            })
        }
    }

    private fun getEmployeeDetail() {
        pbDayDelivery.blockUI(activity as AppCompatActivity)
        vmDashboard.getEmployeeDetail(onSuccess = { employeeDetail ->
            employeeDetail.content?.let {
                //  tvName.text = it.employee_name
                tvName.applySpanPo(
                    it.employee_name.toString(),
                    ""/*"(Present)"*/,
                    R.color.colorAccent
                )
                tvPhoneNo.text = it.mobile_number
                tvAddress.text = it.permanent_address
                activity?.setCircularView(ivProfilePic, BuildConfig.BASE_URL + it.proFileLocation)
            }
            pbDayDelivery.unBlockUI(activity as AppCompatActivity)
        }, onError = {
            activity?.toast(it)
            pbDayDelivery.unBlockUI(activity as AppCompatActivity)
        })
    }

}
