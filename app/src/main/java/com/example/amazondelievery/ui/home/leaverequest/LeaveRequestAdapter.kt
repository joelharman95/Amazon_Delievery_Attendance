package com.example.amazondelievery.ui.home.leaverequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.amazondelievery.BuildConfig
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.response.LeaveManagementItem
import com.example.amazondelievery.di.setCircularView
import kotlinx.android.synthetic.main.layout_leave_request_item.view.*

typealias dialogCallBack = (type: Boolean, id: Int) -> Unit   //  type == true -> Approve, false-> Cancel

class LeaveRequestAdapter(val dialogCallBack: dialogCallBack) :
    RecyclerView.Adapter<LeaveRequestAdapter.LeaveHolder>() {

    private val leaveManagementList = mutableListOf<LeaveManagementItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaveHolder {
        return LeaveHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_leave_request_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = leaveManagementList.size

    override fun onBindViewHolder(holder: LeaveHolder, position: Int) {
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

    inner class LeaveHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindUI(position: Int) {
            view.apply {
                this.changeProperty()

                context.setCircularView(ivProfilePic, BuildConfig.BASE_URL + leaveManagementList[position].profileLink)
                tvName.text = leaveManagementList[position].employeeName
                tvContactNo.text = leaveManagementList[position].mobileNumber
                tvMessage.text = leaveManagementList[position].leaveRequest
                tvDateAndTime.text = leaveManagementList[position].fromDate/* + " to " + leaveManagementList[position].toDate*/
                tvSalaried.text = leaveManagementList[position].type

                btnCancel.setOnClickListener {
                    dialogCallBack.invoke(true, leaveManagementList[position].id as Int)
                }
                btnPending.setOnClickListener {
                    dialogCallBack.invoke(false, leaveManagementList[position].id as Int)
                }
            }
        }

        private fun View.changeProperty() {
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
            tvMessage.setTextColor(ContextCompat.getColor(context, R.color.black))
        }

    }

}