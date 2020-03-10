package com.example.amazondelievery.ui.launch.fragment.home.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.amazondelievery.R
import kotlinx.android.synthetic.main.fragment_employee.*

class EmployeeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvEmployee.adapter = EmployeeAdapter()

        (rvEmployee.adapter as EmployeeAdapter).addDeliveryList()
    }

}
