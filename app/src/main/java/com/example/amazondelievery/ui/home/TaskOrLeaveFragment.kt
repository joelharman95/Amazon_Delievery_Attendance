package com.example.amazondelievery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.request.RequestLeave
import com.example.amazondelievery.data.viewmodel.DashboardViewModel
import com.example.amazondelievery.di.*
import com.example.amazondelievery.di.utility.TaskConstant.CUSTOM
import com.example.amazondelievery.di.utility.TaskConstant.DAY
import com.example.amazondelievery.di.utility.TaskConstant.TIME
import kotlinx.android.synthetic.main.layout_leave_request.*
import kotlinx.android.synthetic.main.layout_leave_request_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskOrLeaveFragment : Fragment() {

    private val vmDashboardViewModel by viewModel<DashboardViewModel>()
    private var Type = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_or_leave, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.setCircularView(ivProfilePic, R.drawable.icon_user)
        initTypeSpinner()
        getLeaveList()

        spType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                if (i > 0) {
                    resetFields(true)
                    when (i) {
                        DAY -> {
                            Type = DAY
                            tvSelectDate.text = resources.getString(R.string.label_select_date)
                            tvSelectTimeDate1.text = resources.getString(R.string.select_end_date)
                            tvSelectTimeDate1.visibility = View.GONE
                            tvSelectTimeDate2.visibility = View.GONE
                        }
                        TIME -> {
                            Type = TIME
                            tvSelectDate.text = resources.getString(R.string.label_select_date)
                            tvSelectTimeDate1.text = resources.getString(R.string.select_start_time)
                            tvSelectTimeDate2.text = resources.getString(R.string.select_end_time)
                            tvSelectTimeDate1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_time,0,0,0)
                            tvSelectTimeDate1.visibility = View.VISIBLE
                            tvSelectTimeDate2.visibility = View.VISIBLE
                        }
                        CUSTOM -> {
                            Type = CUSTOM
                            tvSelectDate.text = resources.getString(R.string.select_start_date)
                            tvSelectTimeDate1.text = resources.getString(R.string.select_end_date)
                            tvSelectTimeDate1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_date,0,0,0)
                            tvSelectTimeDate1.visibility = View.VISIBLE
                            tvSelectTimeDate2.visibility = View.GONE
                        }
                    }
                } else {
                    resetFields(false)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        tvSelectDate.setOnClickListener {
            when (Type) {
                DAY, TIME -> tvSelectDate.showDate(resources.getString(R.string.label_select_date))
                else -> tvSelectDate.showDate(resources.getString(R.string.select_start_date), tvSelectTimeDate1.tag?.toString(), false)
            }
        }

        tvSelectTimeDate1.setOnClickListener {
            if (Type == CUSTOM)
                tvSelectTimeDate1.showDate(resources.getString(R.string.select_end_date), tvSelectDate.tag?.toString(), true)
            else
                tvSelectTimeDate1.showTime(resources.getString(R.string.select_start_time))
        }
        tvSelectTimeDate2.setOnClickListener {
            tvSelectTimeDate2.showTime(resources.getString(R.string.select_end_time))
        }

        btnSendLeaveReq.setOnClickListener {
            val requestLeave = getRequestLeave()
            if (requestLeave.from_date != null && requestLeave.to_date != null) {
                vmDashboardViewModel.sendLeaveRequest(
                    requestLeave = requestLeave,
                    onSuccess = {
                        activity?.toast(it.message.toString())
                        resetFields(false)
                        getLeaveList()
                    },
                    onError = {
                        activity?.toast(it)
                    })
            } else
                activity?.toast("Need to fill necessary fields and must not mismatch")
        }
    }

    private fun initTypeSpinner() {
        spType.adapter = ArrayAdapter<String>(
            activity as AppCompatActivity,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.type)
        )
        (spType.adapter as ArrayAdapter<String>).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    private fun resetFields(isEnabled: Boolean) {
        tvSelectDate.isEnabled = isEnabled
        tvSelectTimeDate1.isEnabled = isEnabled
        tvSelectTimeDate2.isEnabled = isEnabled
        etAddress.isEnabled = isEnabled
        btnSendLeaveReq.isEnabled = isEnabled
        tvSelectDate.text = resources.getString(R.string.label_select_date)
        tvSelectTimeDate1.text = resources.getString(R.string.label_select_time)
        tvSelectTimeDate2.text = resources.getString(R.string.label_select_time)
        tvSelectDate.tag = null
        tvSelectTimeDate1.tag = null
        tvSelectTimeDate2.tag = null
    }

    private fun getRequestLeave(): RequestLeave {
        return when (Type) {
            DAY -> {
                RequestLeave(
                    type = spType.selectedItem.toString(),
                    from_date = tvSelectDate.tag?.toString(),
                    to_date = tvSelectDate.tag?.toString(),
                    leave_request = etAddress.text.toString()
                )
            }
            TIME -> {
                /*val time1 = tvSelectTimeDate2.tag?.toString()?.getTimeInMilli()
                val time2 = tvSelectTimeDate1.tag?.toString()?.getTimeInMilli()
                val diff = time2?.minus(time1.toString().toLong())
                if (diff!= null && diff > 0) {*/
                    RequestLeave(
                        type = spType.selectedItem.toString(),
                        from_date = tvSelectDate.tag?.toString() + " " + tvSelectTimeDate1.tag?.toString(),
                        to_date = tvSelectDate.tag?.toString() + " " + tvSelectTimeDate2.tag?.toString(),
                        leave_request = etAddress.text.toString()
                    )
                /*} else {
                    RequestLeave()
                }*/
            }
            CUSTOM -> {
                RequestLeave(
                    type = spType.selectedItem.toString(),
                    from_date = tvSelectDate.tag?.toString(),
                    to_date = tvSelectTimeDate1.tag?.toString(),
                    leave_request = etAddress.text.toString()
                )
            }
            else -> {
                RequestLeave()
            }
        }
    }

    private fun getLeaveList() {
        vmDashboardViewModel.getLatestLeaveList(10, 0, { leaveList ->
            leaveList.content?.let {
                context?.setCircularView(ivProfilePic, it.profileLink.toString())
                tvName.text = it.employeeName
                tvContactNo.text = it.mobileNumber
                tvMessage.text = it.leaveRequest
                tvDateAndTime.text = it.toDate
                tvSalaried.text = it.type
                if (it.cancel_status == 0)
                    btnCancel.isEnabled = false
                btnPending.isEnabled = false
            }
            btnCancel.setOnClickListener {

            }
        },{
            activity?.toast(it)
        })
    }

}
