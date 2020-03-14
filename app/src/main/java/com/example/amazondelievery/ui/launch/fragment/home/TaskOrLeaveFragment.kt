package com.example.amazondelievery.ui.launch.fragment.home

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.amazondelievery.R
import com.example.amazondelievery.di.setCircularView
import com.example.amazondelievery.di.showDate
import kotlinx.android.synthetic.main.layout_leave_request.*
import kotlinx.android.synthetic.main.layout_leave_request_item.*
import java.util.*

class TaskOrLeaveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_or_leave, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        context?.setCircularView(ivProfilePic, R.drawable.icon_user)

        etSelectTime.setOnClickListener {
            etSelectTime.showTime()
        }
        etSelectDate.setOnClickListener {
            etSelectDate.showDate()
        }
    }

    private fun EditText.showTime() {
        val cal = Calendar.getInstance()
        val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            this.setText("$hourOfDay:$minute")
        }
        TimePickerDialog(
            context,
            timeListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

}
