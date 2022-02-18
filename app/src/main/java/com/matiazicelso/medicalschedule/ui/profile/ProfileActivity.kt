package com.matiazicelso.medicalschedule.ui.profile

import android.os.Build
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.viewModel.ProfileViewModel
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ProfileActivity : AppCompatActivity(R.layout.activity_profile) {

    private val viewModel: ProfileViewModel by viewModels()
    private val loading: FrameLayout by lazy { findViewById(R.id.profile_loading) }
    private val name: TextInputEditText by lazy { findViewById(R.id.profile_name_txt) }
    private val phone: TextInputEditText by lazy { findViewById(R.id.profile_phone_number) }
    private val date: TextInputEditText by lazy { findViewById(R.id.profile_date_txt) }
    private val location: TextInputEditText by lazy { findViewById(R.id.profile_input_location_txt) }
    private val image: ImageView by lazy { findViewById(R.id.profile_img) }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()

        viewModel.loadProfile()

        observeData()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeData() {
        viewModel.loading.observe(this){ loading.isVisible = it }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Deu Erro", Toast.LENGTH_SHORT ).show()
        }

        viewModel.profile.observe(this){


            name.setText("${it.name.first} ${it.name.last} ")
            phone.setText(it.phone)
            date.setText(convertDate(it.dob.date))
            location.setText(it.location.city)
            Glide.with(this).load(it.picture.large).into(image)

            //Toast.makeText(this, "Deu Certo", Toast.LENGTH_SHORT ).show()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertDate(date: String): String{
        val newDate = date.dropLast(1)
        val d = LocalDateTime.parse(newDate)
        return "${d.dayOfMonth}/${d.monthValue}/${d.year}"
    }

}