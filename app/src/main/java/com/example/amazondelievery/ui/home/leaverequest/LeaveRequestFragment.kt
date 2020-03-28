package com.example.amazondelievery.ui.home.leaverequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.response.LeaveManagementItem
import com.example.amazondelievery.data.viewmodel.DashboardViewModel
import com.example.amazondelievery.di.blockUI
import com.example.amazondelievery.di.toast
import com.example.amazondelievery.di.unBlockUI
import com.example.amazondelievery.di.utility.PaginationListener
import kotlinx.android.synthetic.main.fragment_leave_request.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaveRequestFragment : Fragment() {

    private val vmDashboard by viewModel<DashboardViewModel>()
    private var currentPage = 0
    private var isLastPage = false
    private val PAGE_COUNT = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leave_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvLeaveRequest.adapter = LeaveRequestAdapter { type, imId ->
            when (type) {
                true -> {
                    ApproveRequestDialog.showApproveDialog(childFragmentManager) { typeFromDialog, _ ->
                        if (typeFromDialog) {
                            postLeaveManagement(1, imId)
                        }
                    }
                }
                false -> {
                    postLeaveManagement(0, imId)
                }
            }
        }

        getInitialList()

        rvLeaveRequest.addOnScrollListener(object : PaginationListener() {
            override fun loadMore() {
                currentPage++
                doApiCall()
            }
        })
    }

    private fun doApiCall() {
        if (!isLastPage) {
            pbLeaveRequest.blockUI(activity as AppCompatActivity)
            vmDashboard.getSupervisorLeaveList(PAGE_COUNT, currentPage, {
                (rvLeaveRequest.adapter as LeaveRequestAdapter).addDeliveryList(it.content?.leaveManagement as List<LeaveManagementItem>)
                pbLeaveRequest.unBlockUI(activity as AppCompatActivity)
            }, {
                isLastPage = true
                activity?.toast(it)
                pbLeaveRequest.unBlockUI(activity as AppCompatActivity)
            })
        }
    }

    private fun getInitialList() {
        pbLeaveRequest.blockUI(activity as AppCompatActivity)
        vmDashboard.getSupervisorLeaveList(PAGE_COUNT, currentPage, {
            it.content?.leaveManagement.let { leaveManagement ->
                (rvLeaveRequest.adapter as LeaveRequestAdapter).addDeliveryList(leaveManagement as List<LeaveManagementItem>)
                if (!leaveManagement.isNullOrEmpty()) {
                    tvNoLeaveRequest.visibility = View.GONE
                }
            }
            pbLeaveRequest.unBlockUI(activity as AppCompatActivity)
        }, {
            activity?.toast(it)
            println("GET____ERROR_____" + it)
            pbLeaveRequest.unBlockUI(activity as AppCompatActivity)
        })
    }

    private fun postLeaveManagement(status: Int, imId: Int) {
        pbLeaveRequest.blockUI(activity as AppCompatActivity)
        vmDashboard.postLeaveManagement(status, imId, {
            activity?.toast(it.message.toString())
            currentPage = 0
            (rvLeaveRequest.adapter as LeaveRequestAdapter).clearDeliveryList()
            getInitialList()
            pbLeaveRequest.unBlockUI(activity as AppCompatActivity)
        }, {
            pbLeaveRequest.unBlockUI(activity as AppCompatActivity)
            activity?.toast(it)
        })
    }

}
