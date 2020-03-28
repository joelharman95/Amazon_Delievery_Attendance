package com.example.amazondelievery.ui.home.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.response.EmployeeDelAssociate
import com.example.amazondelievery.data.api.response.LeaveManagementItem
import com.example.amazondelievery.data.viewmodel.DashboardViewModel
import com.example.amazondelievery.di.blockUI
import com.example.amazondelievery.di.toast
import com.example.amazondelievery.di.unBlockUI
import com.example.amazondelievery.di.utility.PaginationListener
import kotlinx.android.synthetic.main.fragment_employee.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmployeeFragment : Fragment() {

    private val vmDashboard by viewModel<DashboardViewModel>()
    private var currentPage = 0
    private var isLastPage = false
    private val PAGE_COUNT = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvEmployee.adapter = EmployeeAdapter()

        pbEmployee.blockUI(activity as AppCompatActivity)
        vmDashboard.getDelAssociateteList(PAGE_COUNT, currentPage, {
            (rvEmployee.adapter as EmployeeAdapter).addEmployeeList(it.content?.employeeDelAssociateList as List<EmployeeDelAssociate>)
            if (!it.content.employeeDelAssociateList.isNullOrEmpty())
                tvNoEmployee.visibility = View.GONE
            pbEmployee.unBlockUI(activity as AppCompatActivity)
        }, {
            activity?.toast(it)
            pbEmployee.unBlockUI(activity as AppCompatActivity)
        })

        rvEmployee.addOnScrollListener(object : PaginationListener() {
            override fun loadMore() {
                currentPage++
                doApiCall()
            }
        })
    }

    private fun doApiCall() {
        if (!isLastPage) {
            pbEmployee.blockUI(activity as AppCompatActivity)
            vmDashboard.getDelAssociateteList(PAGE_COUNT, currentPage, {
                (rvEmployee.adapter as EmployeeAdapter).addEmployeeList(it.content?.employeeDelAssociateList as List<EmployeeDelAssociate>)
                pbEmployee.unBlockUI(activity as AppCompatActivity)
            }, {
                isLastPage = true
                activity?.toast(it)
                pbEmployee.unBlockUI(activity as AppCompatActivity)
            })
        }
    }

}
