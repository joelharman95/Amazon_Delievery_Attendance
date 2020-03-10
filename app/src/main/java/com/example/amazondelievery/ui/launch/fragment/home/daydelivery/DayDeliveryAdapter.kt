package com.example.amazondelievery.ui.launch.fragment.home.daydelivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.amazondelievery.R
import com.example.amazondelievery.di.setCircularView
import kotlinx.android.synthetic.main.layout_leave_request_item.view.*

class DayDeliveryAdapter : RecyclerView.Adapter<DayDeliveryAdapter.DeliveryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryHolder {
        return DeliveryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_leave_request_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = 6

    override fun onBindViewHolder(holder: DeliveryHolder, position: Int) {
        holder.bindUI(position)
    }

    fun addDeliveryList() {
        //  Use this fun to add list on the main list
        notifyDataSetChanged()
    }

    inner class DeliveryHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindUI(position: Int) {
            view.apply {
                this.changeProperty()
                context.setCircularView(ivProfilePic, R.drawable.icon_user)
            }
        }

        private fun View.changeProperty() {
            btnCancel.apply {
                text = ""
                icon = ContextCompat.getDrawable(context, R.drawable.icon_check)
                setBackgroundColor(ContextCompat.getColor(context, R.color.green))
            }
            btnPending.apply {
                text = ""
                icon = ContextCompat.getDrawable(context, R.drawable.icon_close)
                setBackgroundColor(ContextCompat.getColor(context, R.color.red))
            }
            tvMessage.apply {
                setLines(1)
                setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }
    }

}