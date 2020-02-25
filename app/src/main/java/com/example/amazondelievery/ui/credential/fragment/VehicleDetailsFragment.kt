package com.example.amazondelievery.ui.credential.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import kotlinx.android.synthetic.main.fragment_vehicle_details.*

class VehicleDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vehicle_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_vehicle_to_dp)
        }
    }

}
