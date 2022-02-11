package com.matiazicelso.medicalschedule.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matiazicelso.medicalschedule.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.supportActionBar?.hide()


        supportFragmentManager.beginTransaction()
            .replace(R.id.login_frag_container, LoginFragment())
            .commit()
    }

}
