package com.example.amazondelievery.ui.launch.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.amazondelievery.R
import com.example.amazondelievery.di.setCircularView
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.setCircularView(imageView = ivProfilePic, pic = R.drawable.icon_user)
        activity?.setCircularView(imageView = ivTakePic, pic = R.drawable.icon_camera)

        llCompleteDelivery.setOnClickListener {
            ilDelivery.visibility = View.VISIBLE
            group1.visibility = View.GONE
        }

        llTotalDelivery.setOnClickListener {
            ilDelivery.visibility = View.VISIBLE
            group1.visibility = View.GONE
        }

    }


}
