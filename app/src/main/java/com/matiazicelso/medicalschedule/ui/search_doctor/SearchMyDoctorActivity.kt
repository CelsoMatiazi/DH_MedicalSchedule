package com.matiazicelso.medicalschedule.ui.search_doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.viewModel.DoctorViewModel


class SearchMyDoctorActivity : AppCompatActivity(R.layout.activity_search_my_doctor) {

    private val viewModel: DoctorViewModel by viewModels()
    private val loading: FrameLayout by lazy { findViewById(R.id.search_loading) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()

        viewModel.loadDoctor()
        observeData()
    }


    private fun observeData() {
        viewModel.loading.observe(this){ loading.isVisible = it }

        viewModel.error.observe(this) {
            if(it){
                Toast.makeText(this, "Deu Erro", Toast.LENGTH_SHORT ).show()
            }
        }

        viewModel.doctors.observe(this){
            println("DOCTORS")
            println(it.results[0].name)
            println(it.results[0].classification)
            println(it.results[0].specialization)
            println(it.results[0].views)
        }
    }


}