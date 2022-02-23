package com.matiazicelso.medicalschedule.ui.login


import android.app.AlertDialog
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

        viewModel.loginStatus.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(R.id.action_loginFragment_to_profileActivity)
            }else{
                showDialog()
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


    private fun showDialog(){
        val items = arrayOf("Verifique o seu usuario ou senha.")
        AlertDialog
            .Builder(context)
            .setTitle("Login invalido")
            .setItems(items){dialog, item -> dialog.dismiss()}
            .show()

    }

}
