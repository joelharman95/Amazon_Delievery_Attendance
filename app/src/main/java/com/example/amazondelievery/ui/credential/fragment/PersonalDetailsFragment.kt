package com.example.amazondelievery.ui.credential.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import com.example.amazondelievery.di.Constants.PERSONAL_DETAILS
import kotlinx.android.synthetic.main.fragment_personal_details.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class PersonalDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //activity.initToolbar(tbar, )

        btnNext.setOnClickListener {
            when (findNavController().currentDestination?.id) {
                R.id.personalDetailsFrag -> {
                    findNavController().navigate(R.id.action_details_to_vehicle)
                }
            }
        }
    }

    private fun initToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(tbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = PERSONAL_DETAILS
            setDisplayHomeAsUpEnabled(true)
        }
        tbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

}
