package com.matiazicelso.medicalschedule.ui.search_doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.data.factory.DatabaseFactory
import com.matiazicelso.medicalschedule.data.local.AppDatabase
import com.matiazicelso.medicalschedule.data.local.DbDoctorHelper
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntity
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_API_ID
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_CLASSIFICATION
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_EXPERIENCE
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_NAME
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_PATIENT_STORIES
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_PHOTO
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_SPECIALIZATION
import com.matiazicelso.medicalschedule.data.local.entity.DoctorEntry.COLUMN_NAME_VIEWS
import com.matiazicelso.medicalschedule.data.model.DoctorItem
import com.matiazicelso.medicalschedule.viewModel.DoctorViewModel


class SearchMyDoctorActivity : AppCompatActivity(R.layout.activity_search_my_doctor) {

    private val viewModel: DoctorViewModel by viewModels()
    private val loading: FrameLayout by lazy { findViewById(R.id.search_loading) }
    private val recycler: RecyclerView by lazy { findViewById<RecyclerView>(R.id.search_recycler) }
    private val adapter = SearchDoctorAdapter()
    var page: Int = 1
    var pageLimit: Int = 1
    var doctorListDb = mutableListOf<DoctorItem>()

    private lateinit var dbDoctorHelper : DbDoctorHelper
    private lateinit var dbRoom: AppDatabase

    private lateinit var floatingBtn : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()

        dbDoctorHelper = DbDoctorHelper(this)
        dbRoom = DatabaseFactory.getDatabase(this)

        floatingBtn = findViewById(R.id.floating_btn_doctors)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter

        floatingBtn.setOnClickListener {
            adapter.updateListDB(doctorListDb)
            adapter.notifyDataSetChanged()
        }

        viewModel.loadDoctor(page)
        setScrollView() //controla a paginação
        observeData()
    }


    private fun saveDoctorsDb(doctors: List<DoctorItem>){
        doctors.forEach{ doctor ->
            save(doctor)
        }
    }


    private fun save(doctor: DoctorItem){
        println("SAVE")
        val id = dbDoctorHelper.insert {
            put(COLUMN_NAME_NAME, doctor.name + " *")
            put(COLUMN_NAME_PHOTO, doctor.photo)
            put(COLUMN_NAME_SPECIALIZATION, doctor.specialization)
            put(COLUMN_NAME_CLASSIFICATION, doctor.classification.toString())
            put(COLUMN_NAME_EXPERIENCE, "")
            put(COLUMN_NAME_PATIENT_STORIES, "")
            put(COLUMN_NAME_VIEWS, doctor.views.toString())
            put(COLUMN_NAME_API_ID, doctor.id)
        }

        println(id)
        listWithId(doctor.id)
       // deleteDoctor(id)

        //++++++++++++++++++ ROOM ++++++++++++++++++++++

        val myDoctor = DoctorEntity(
            name = "DR. Adalberto",
            photo = "url da foto"
        )

        dbRoom.doctorDao().insert(myDoctor)
        val doctors = dbRoom.doctorDao().listAll()
        println(doctors)

    }

    private fun deleteDoctor(id: Long) {
       dbDoctorHelper.delete(id)
    }

    private fun listWithId(name: String){
        doctorListDb.addAll(dbDoctorHelper.listAllWithId(name))
    }


    private fun observeData() {
        viewModel.loading.observe(this){ loading.isVisible = it }

        viewModel.error.observe(this) {
            if(it){
                Toast.makeText(this, "Deu Erro", Toast.LENGTH_SHORT ).show()
            }
        }

        viewModel.doctors.observe(this){

            adapter.updateList(it.doctors)
            pageLimit = it.limit_page

            saveDoctorsDb(it.doctors)

        }
    }


    private fun setScrollView(){
        recycler.run {
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val target = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = target.itemCount
                    val lastVisible: Int = target.findLastVisibleItemPosition()
                    val lastItem: Boolean = lastVisible + 5 >= totalItemCount

                    if( (totalItemCount > 0 && lastItem) && (page < pageLimit && loading.isVisible.not()) ){
                        page++
                        viewModel.loadDoctor(page)

                        println("PRINT PAGE")
                        println(page)

                    }
                }
            })
        }
    }

    override fun onStop() {
        DatabaseFactory.removeInstance()
        super.onStop()
    }


}