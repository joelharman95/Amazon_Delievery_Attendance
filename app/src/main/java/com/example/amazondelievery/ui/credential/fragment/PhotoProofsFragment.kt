package com.example.amazondelievery.ui.credential.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import kotlinx.android.synthetic.main.fragment_photo_proofs.*

class PhotoProofsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_proofs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnComplete.setOnClickListener {
            findNavController().navigate(R.id.action_dp_to_validation)
        }
    }

}
