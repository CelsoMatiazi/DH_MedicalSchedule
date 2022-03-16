package com.matiazicelso.medicalschedule.ui.search_doctor

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.matiazicelso.medicalschedule.R
import com.matiazicelso.medicalschedule.data.model.DoctorItem
import com.matiazicelso.medicalschedule.data.model.DoctorResponse

class SearchDoctorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private val diffUtil = AsyncListDiffer(this, DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DoctorsViewHolder(inflater.inflate(R.layout.doctor_card_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is DoctorsViewHolder -> holder.bind(diffUtil.currentList[position])
        }
    }

    override fun getItemCount(): Int = diffUtil.currentList.size

    fun updateList(items: List<DoctorItem>){
        diffUtil.submitList(diffUtil.currentList.plus(items))

    }

    fun updateListDB(items: List<DoctorItem>){
        diffUtil.submitList(items)

    }



    companion object{
        val DIFF_UTIL = object : DiffUtil.ItemCallback<DoctorItem>() {

            override fun areItemsTheSame(oldItem: DoctorItem, newItem: DoctorItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DoctorItem, newItem: DoctorItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}


class DoctorsViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val image : ImageView = view.findViewById(R.id.doctor_card_image)
    private val name : TextView = view.findViewById(R.id.card_doctor_name)
    private val specialization : TextView = view.findViewById(R.id.card_doctor_specilist)
    private val classification : TextView = view.findViewById(R.id.card_doctor_percent)
    private val views : TextView = view.findViewById(R.id.card_doctor_patient)

    fun bind(item: DoctorItem){
        Glide.with(image.context).load(item.photo).into(image)
        name.text = item.name
        specialization.text = item.specialization
        classification.text = item.classification.toString()
        views.text = item.views.toString()

    }
}