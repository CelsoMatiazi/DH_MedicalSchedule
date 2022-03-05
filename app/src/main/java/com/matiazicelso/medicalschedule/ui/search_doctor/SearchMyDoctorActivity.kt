package com.matiazicelso.medicalschedule.ui.search_doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.viewModel.DoctorViewModel


class SearchMyDoctorActivity : AppCompatActivity(R.layout.activity_search_my_doctor) {

    private val viewModel: DoctorViewModel by viewModels()
    private val loading: FrameLayout by lazy { findViewById(R.id.search_loading) }
    private val recycler: RecyclerView by lazy { findViewById<RecyclerView>(R.id.search_recycler) }
    private val adapter = SearchDoctorAdapter()
    var page: Int = 1
    var pageLimit: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter

        viewModel.loadDoctor(page)
        setScrollView()
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

            adapter.updateList(it.doctors)
            pageLimit = it.limit_page

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

                    if((totalItemCount > 0 && lastItem) && (page < pageLimit)){
                        page++
                        viewModel.loadDoctor(page)

                        println("PRINT PAGE")
                        println(page)

                    }
                }
            })
        }
    }


}