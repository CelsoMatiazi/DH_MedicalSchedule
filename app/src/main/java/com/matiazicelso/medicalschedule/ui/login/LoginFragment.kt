package com.matiazicelso.medicalschedule.ui.login


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
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

        progressBar = view.findViewById(R.id.login_progressBar)


        viewModel.progressBar.observe(viewLifecycleOwner){
            loading(it)
        }

        viewModel.login.observe(viewLifecycleOwner){
            if(it){
                val snackBar = Snackbar.make(view, "Login efetuado com sucesso", Snackbar.LENGTH_SHORT)
                snackBar.setBackgroundTint(Color.parseColor("#0EBE7F")).show()
            }else{
                val snackBar = Snackbar.make(view, "Dados invalidos!", Snackbar.LENGTH_SHORT)
                snackBar.setBackgroundTint(Color.RED).show()
            }
        }


        loginBtn.setOnClickListener {
            login(view)
        }


        forgotBtn.setOnClickListener {
            parentFragmentManager.let {
                ForgotPasswordFragment.newInstance(Bundle()).apply {
                    show(it, tag)
                }
            }
        }

        newAccountBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

    }

    private fun loading(status: Boolean){
        progressBar.isVisible = status
    }


    private fun login(view: View){
        val email = view.findViewById<TextInputEditText>(R.id.login_email)
        val password = view.findViewById<TextInputEditText>(R.id.login_password)

        viewModel.makeLogin(email?.text.toString(), password?.text.toString())

    }

}
