package com.matiazicelso.medicalschedule.ui.start_app


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.ui.login.LoginActivity

class PresentationThreeFragment : Fragment(R.layout.fragment_presentation_three) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nextBtn = view.findViewById<Button>(R.id.presentation_three_btn)

        nextBtn.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }


}