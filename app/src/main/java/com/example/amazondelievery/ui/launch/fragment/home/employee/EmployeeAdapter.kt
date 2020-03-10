package com.example.amazondelievery.ui.launch.fragment.home.employee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazondelievery.R
import com.example.amazondelievery.di.setCircularView
import kotlinx.android.synthetic.main.layout_employee_item.view.*

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        return EmployeeHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_employee_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = 6

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        holder.bindUI(position)
    }

    fun addDeliveryList() {
        //  Use this fun to add list on the main list
        notifyDataSetChanged()
    }

    inner class EmployeeHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindUI(position: Int) {
            view.apply {
                context.setCircularView(ivProfilePic, R.drawable.icon_user)
            }
        }

    }

}