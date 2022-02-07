package com.matiazicelso.medicalschedule.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.matiazicelso.medicalschedule.R

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val forgotBtn = view.findViewById<TextView>(R.id.login_forgot)
        val newAccountBtn = view.findViewById<TextView>(R.id.login_new_account)


        forgotBtn.setOnClickListener {
            parentFragmentManager.let {
                ForgotPasswordFragment.newInstance(Bundle()).apply {
                    show(it, tag)
                }
            }
        }

        newAccountBtn.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.login_frag_container, SignUpFragment())
                .commit()
        }


    }




}
