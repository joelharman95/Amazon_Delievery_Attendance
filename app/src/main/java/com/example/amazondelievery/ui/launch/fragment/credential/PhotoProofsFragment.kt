package com.example.amazondelievery.ui.launch.fragment.credential

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import com.example.amazondelievery.di.initToolbar
import com.example.amazondelievery.di.utility.Constants
import kotlinx.android.synthetic.main.fragment_photo_proofs.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class PhotoProofsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_proofs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.initToolbar(tbar, Constants.PHOTO_PROOFS, findNavController())

        //  val reqAllDetails = arguments?.get(Constants.VEHICLE_DETAILS) as ReqAllDetails

        btnComplete.setOnClickListener {

            when (findNavController().currentDestination?.id) {

                R.id.photoProofsFrag -> findNavController().navigate(
                    R.id.action_dp_to_validation,
                    null,
                    NavOptions.Builder().setPopUpTo(0, false).build()
                )

                /*R.id.photoProofsFrag -> findNavController().navigate(
                    R.id.action_dp_to_validation*//*,       //  Incase if using activity
                    null, null,
                    ActivityNavigator.Extras.Builder()
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .build()*//*
                )*/
            }
        }
    }

}
