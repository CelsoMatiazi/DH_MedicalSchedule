package com.matiazicelso.medicalschedule.ui.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.viewModel.LoginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()
    lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val forgotBtn = view.findViewById<TextView>(R.id.login_forgot)
        val newAccountBtn = view.findViewById<TextView>(R.id.login_new_account)
        val loginBtn = view.findViewById<TextView>(R.id.login_btn)
        val email = view.findViewById<TextInputEditText>(R.id.login_email)
        val password = view.findViewById<TextInputEditText>(R.id.login_password)
        progressBar = view.findViewById(R.id.login_progressBar)



        loginBtn.setOnClickListener {
            loading(true)
            viewModel.makeLogin(email.text.toString(), password.text.toString())

        }

        viewModel.login.observe(viewLifecycleOwner){
            loading(false)
            if(it){
                Toast.makeText(view.context, "Sucesso", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(view.context, "Usuario invalido", Toast.LENGTH_SHORT).show()
            }
        }

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

    private fun loading(status: Boolean){
        progressBar.isVisible = status
    }




}
