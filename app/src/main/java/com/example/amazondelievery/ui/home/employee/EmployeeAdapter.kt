package com.example.amazondelievery.ui.home.employee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.amazondelievery.BuildConfig
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.response.EmployeeDelAssociate
import com.example.amazondelievery.di.setCircularView
import kotlinx.android.synthetic.main.layout_employee_item.view.*

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder>() {

    private val leaveManagementList = mutableListOf<EmployeeDelAssociate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        return EmployeeHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_employee_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = leaveManagementList.size

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        holder.bindUI(position)
    }

    fun addEmployeeList(mLeaveManagementList: List<EmployeeDelAssociate>) {
        leaveManagementList.addAll(mLeaveManagementList)
        //  notifyDataSetChanged()
        notifyItemRangeInserted(itemCount, leaveManagementList.size - 1)
    }

    inner class EmployeeHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindUI(position: Int) {
            view.apply {
                tvName.text = leaveManagementList[position].employeeName
                tvContactNo.text = leaveManagementList[position].mobileNumber
                tvInsuranceExpDate.text = leaveManagementList[position].bikeInsuranceExpDate
                //  tvLeave.text = leaveManagementList[position].leaveRequest
                //  tvStatus.text = leaveManagementList[position].status
                tvBase.text = leaveManagementList[position].associateType
                tvBikeNo.text = leaveManagementList[position].bikeNumber
                tvBikeModel.text = leaveManagementList[position].bikeModel
                tvInsuranceExpDate.setTextColor(ContextCompat.getColor(context, R.color.red))
                context.setCircularView(
                    ivProfilePic,
                    BuildConfig.BASE_URL + leaveManagementList[position].proFilePic
                )
            }
        }

    }

}