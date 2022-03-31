package com.matiazicelso.medicalschedule.ui.login

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.matiazicelso.medicalschedule.R
import java.security.MessageDigest


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.supportActionBar?.hide()


    }

}
