package com.matiazicelso.medicalschedule.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matiazicelso.medicalschedule.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        this.supportActionBar?.hide()
    }
}