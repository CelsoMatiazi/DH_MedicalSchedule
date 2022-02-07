package com.matiazicelso.medicalschedule.ui.login

import android.app.ProgressDialog.show
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.matiazicelso.medicalschedule.R

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val forgotBtn = view.findViewById<TextView>(R.id.login_forgot)


        forgotBtn.setOnClickListener {

            parentFragmentManager.let {
                ForgotPasswordFragment.newInstance(Bundle()).apply {
                    show(it, tag)
                }
            }

        }
    }




}