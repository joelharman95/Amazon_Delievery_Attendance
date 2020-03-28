package com.example.amazondelievery.ui.home.daydelivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.amazondelievery.BuildConfig
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.response.LeaveManagementItem
import com.example.amazondelievery.di.applySpanPo
import com.example.amazondelievery.di.setCircularView
import kotlinx.android.synthetic.main.layout_leave_request_item.view.*

class DayDeliveryAdapter : RecyclerView.Adapter<DayDeliveryAdapter.DeliveryHolder>() {

    private val leaveManagementList = mutableListOf<LeaveManagementItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryHolder {
        return DeliveryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_leave_request_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = leaveManagementList.size

    override fun onBindViewHolder(holder: DeliveryHolder, position: Int) {
        holder.bindUI(position)
    }

    fun addDeliveryList(mLeaveManagementList: List<LeaveManagementItem>) {
        leaveManagementList.addAll(mLeaveManagementList)
      //  notifyDataSetChanged()
        notifyItemRangeInserted(itemCount, leaveManagementList.size-1)
    }

    fun clearDeliveryList() {
        leaveManagementList.clear()
        notifyDataSetChanged()
    }

    inner class DeliveryHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindUI(position: Int) {
            view.apply {
                context.setCircularView(ivProfilePic, BuildConfig.BASE_URL + leaveManagementList[position].profileLink)
                tvName.text = leaveManagementList[position].employeeName
                tvContactNo.text = leaveManagementList[position].mobileNumber
                tvMessage.text = leaveManagementList[position].bikeNumber
                tvDateAndTime.text = leaveManagementList[position].fromDate
                changeProperty()
                tvSalaried.applySpanPo("5/", "20", R.color.red)    //  TODO  ::  ::  24-03-2020  ////  Need to add these two fields in response
            }
        }

        private fun View.changeProperty() {
            tvSalaried.setTextColor(ContextCompat.getColor(context, R.color.black))
            tvName.setTextColor(ContextCompat.getColor(context, R.color.black))
            tvDateAndTime.text = resources.getString(R.string.delivery_count)
            btnCancel.apply {
                text = ""
                icon = ContextCompat.getDrawable(context, R.drawable.icon_check)
                setBackgroundColor(ContextCompat.getColor(context, R.color.green))
            }
            btnPending.apply {
                text = ""
                icon = ContextCompat.getDrawable(context, R.drawable.icon_close)
                setBackgroundColor(ContextCompat.getColor(context, R.color.red))
            }
            tvMessage.apply {
                setLines(1)
                setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }
    }

}