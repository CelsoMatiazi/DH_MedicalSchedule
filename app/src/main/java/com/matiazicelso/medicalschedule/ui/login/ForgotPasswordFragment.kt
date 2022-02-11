package com.matiazicelso.medicalschedule.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.matiazicelso.medicalschedule.R


class ForgotPasswordFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forgot_password_, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val continueBtn = view.findViewById<Button>(R.id.forgot_continue_btn)

        continueBtn.setOnClickListener {
            dismiss()
            parentFragmentManager.let {
                ResetPasswordFragment.newInstance(Bundle()).apply {
                    show(it, tag)
                }
            }

        }


    }

    companion object{
        @JvmStatic
        fun newInstance(bundle: Bundle): ForgotPasswordFragment {
            val fragment = ForgotPasswordFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}
