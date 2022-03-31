package com.matiazicelso.medicalschedule.ui.start_app

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.ui.login.LoginFragment
import java.security.MessageDigest

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        supportActionBar?.hide()

//        val info = packageManager.getPackageInfo(
//            "com.matiazicelso.medicalschedule",
//            PackageManager.GET_SIGNATURES
//        )
//        for (signature in info.signatures) {
//            val md: MessageDigest = MessageDigest.getInstance("SHA")
//            md.update(signature.toByteArray())
//            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
//        }

    }
}
