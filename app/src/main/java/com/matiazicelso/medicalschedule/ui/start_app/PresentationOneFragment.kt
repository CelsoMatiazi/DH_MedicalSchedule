package com.matiazicelso.medicalschedule.ui.start_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.matiazicelso.medicalschedule.R

class PresentationOneFragment : Fragment(R.layout.fragment_presentaion_one,) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nextBtn = view.findViewById<Button>(R.id.presentation_one_btn)
        val skipBtn = view.findViewById<TextView>(R.id.presentation_one_skip)

        nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_presentationOneFragment_to_presentationTwoFragment)
        }
    }


}
