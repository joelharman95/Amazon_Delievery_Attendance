package com.example.amazondelievery.ui.home.leaverequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.amazondelievery.R
import kotlinx.android.synthetic.main.dialog_approve_request.*

class ApproveRequestDialog(private val dialogCallBack: dialogCallBack) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_approve_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)

        tvCancel.setOnClickListener {
            dismiss()
        }

        tvOk.setOnClickListener {
            dialogCallBack.invoke(true, 0)
            dismiss()
        }
    }

    companion object {
        fun showApproveDialog(fragmentManager: FragmentManager, dialogCallBack: dialogCallBack) =
            ApproveRequestDialog(dialogCallBack).show(
                fragmentManager,
                ApproveRequestDialog.javaClass.simpleName
            )
    }

}