package com.example.amazondelievery.ui.launch.fragment.home.daydelivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.amazondelievery.R
import com.example.amazondelievery.di.setCircularView
import kotlinx.android.synthetic.main.fragment_day_delivery.*

class DayDeliveryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_day_delivery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.setCircularView(imageView = ivTakePic, pic = R.drawable.icon_camera)
        rvDayDelivery.adapter = DayDeliveryAdapter()

        (rvDayDelivery.adapter as DayDeliveryAdapter).addDeliveryList()
    }

}
