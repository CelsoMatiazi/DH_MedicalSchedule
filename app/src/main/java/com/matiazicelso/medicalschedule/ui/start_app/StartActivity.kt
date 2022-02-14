package com.matiazicelso.medicalschedule.ui.start_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.ui.login.LoginFragment

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        supportActionBar?.hide()

    }
}
