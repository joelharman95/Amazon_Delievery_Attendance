package com.example.amazondelievery.ui.launch.fragment.home.leaverequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.amazondelievery.R
import com.example.amazondelievery.di.toast
import kotlinx.android.synthetic.main.fragment_leave_request.*

class LeaveRequestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leave_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvLeaveRequest.adapter = LeaveRequestAdapter { type, position ->
            when (type) {
                true -> {
                    ApproveRequestDialog.showApproveDialog(childFragmentManager) { typeFromDialog, _ ->
                        if (typeFromDialog) {
                            activity?.toast("Approved for $position")
                        }
                    }
                }
                false -> {
                    activity?.toast("Rejected for $position")
                }
            }
        }

        (rvLeaveRequest.adapter as LeaveRequestAdapter).addDeliveryList()
    }

}
