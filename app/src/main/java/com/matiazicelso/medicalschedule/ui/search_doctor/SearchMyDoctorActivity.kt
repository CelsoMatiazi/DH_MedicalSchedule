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
import com.matiazicelso.medicalschedule.data.model.DoctorItem
import com.matiazicelso.medicalschedule.data.netWork.DataResult
import com.matiazicelso.medicalschedule.viewModel.DoctorViewModel


class SearchMyDoctorActivity : AppCompatActivity(R.layout.activity_search_my_doctor) {

    private val viewModel: DoctorViewModel by viewModels()
    private val loading: FrameLayout by lazy { findViewById(R.id.search_loading) }
    private val recycler: RecyclerView by lazy { findViewById(R.id.search_recycler) }
    private val adapter = SearchDoctorAdapter(::save)
    var page: Int = 1
    var pageLimit: Int = 1
    var doctorListDb = mutableListOf<DoctorItem>()


    private lateinit var floatingBtn : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()

        floatingBtn = findViewById(R.id.floating_btn_doctors)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter

        floatingBtn.setOnClickListener {
            adapter.updateListDB(doctorListDb)
            //adapter.notifyDataSetChanged()
        }

        setScrollView() //controla a paginação
        loadDoctors(page)
    }


    private fun saveDoctorsDb(doctors: List<DoctorItem>){
        doctors.forEach{ doctor ->
            save(doctor)
        }
    }


    private fun save(doctor: DoctorItem){

        viewModel.addOrRemoveFavorite(doctor).observe(this){
            if(it is DataResult.Success){
                adapter.updateItem(it.data)
            }

        }

    }

    private fun loadDoctors(page: Int) {

        viewModel.loadDoctor(page).observe(this){
            when(it){
                is DataResult.Loading -> {loading.isVisible = it.isLoading}
                is DataResult.Error -> {
                    Toast.makeText(this, "Deu Erro", Toast.LENGTH_SHORT ).show()
                }
                is DataResult.Empty -> {}
                is DataResult.Success -> {
                    adapter.updateList(it.data.doctors)
                    pageLimit = it.data.limit_page
                }
            }
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
                        loadDoctors(page)
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