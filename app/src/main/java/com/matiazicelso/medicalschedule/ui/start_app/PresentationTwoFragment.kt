package com.matiazicelso.medicalschedule.ui.start_app


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.matiazicelso.medicalschedule.R

class PresentationTwoFragment : Fragment(R.layout.fragment_presentation_two){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nextBtn = view.findViewById<Button>(R.id.presentation_two_btn)
        val skipBtn = view.findViewById<TextView>(R.id.presentation_two_skip)

        nextBtn.setOnClickListener {
            sendToPresentationThree()
        }

        skipBtn.setOnClickListener {
            sendToPresentationThree()
        }

    }

    private fun sendToPresentationThree(){
        findNavController().navigate(R.id.action_presentationTwoFragment_to_presentationThreeFragment)
    }
}
