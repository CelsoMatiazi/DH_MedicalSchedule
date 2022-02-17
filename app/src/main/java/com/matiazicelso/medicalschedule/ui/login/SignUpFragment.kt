package com.matiazicelso.medicalschedule.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.ui.profile.ProfileActivity


class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backToLogin = view.findViewById<TextView>(R.id.signup_have_account)
        val signup = view.findViewById<TextView>(R.id.signup_btn)

        backToLogin.setOnClickListener {
            activity?.onBackPressed()
        }

        signup.setOnClickListener {
            val intent = Intent(view.context, ProfileActivity::class.java)
            startActivity(intent)
        }


    }



}
