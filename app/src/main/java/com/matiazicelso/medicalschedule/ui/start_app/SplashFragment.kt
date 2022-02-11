package com.matiazicelso.medicalschedule.ui.start_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.ui.login.LoginFragment

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({

            parentFragmentManager.beginTransaction()
                .replace(R.id.startApp_frag_container, PresentationOneFragment())
                .commit()
        }, 4000)

    }


}
