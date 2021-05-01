package com.cherish.speedroommatingapp.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import com.cherish.speedroommatingapp.R
import com.cherish.speedroommatingapp.di.module.GlideApp
import com.cherish.speedroommatingapp.model.mdata.UpcomingEventsData
import com.cherish.speedroommatingapp.utils.DateUtils

import kotlinx.android.synthetic.main.upcoming_item_layout.view.*

class IncomingEventsAdapter(val callback: (Int) -> Unit) :
    ListAdapter<UpcomingEventsData, IncomingEventsAdapter.IncomingViewHolder>(PojoDiffCallback()){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.upcoming_item_layout, parent, false)
        return IncomingViewHolder(view,callback)
    }



    override fun onBindViewHolder(holder: IncomingViewHolder, position: Int) {
       val upcomingData = getItem(position)
        if (upcomingData!= null) {
            holder.cost.text = upcomingData.cost
            holder.location.text = upcomingData.location
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val newDate = DateUtils.formatDate(upcomingData.end_time!!)
                val startTime =
                    DateUtils.formatTimeNewApi(upcomingData.start_time!!, upcomingData.end_time)
                holder.date.text = newDate
                holder.time.text = startTime
            } else {
                val oldDate = DateUtils.formatDateOldApi(upcomingData.end_time!!)
                val startTime =
                    DateUtils.formatTimeOldApi(upcomingData.start_time!!, upcomingData.end_time)

                holder.date.text = oldDate
                holder.time.text = startTime
            }

            holder.venue.text = upcomingData.venue
            GlideApp.with(holder.image)
                .asDrawable()
                .override(200, 200)
                .centerCrop()
                .load(upcomingData.image_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(ObjectKey(System.currentTimeMillis() / (1000*60*60*24)))
                .into(object :
                    CustomTarget<Drawable>(200, 200) {
                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        holder.image.background=resource
                    }

                })

        }


    }




    class IncomingViewHolder(itemView : View , callback: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val cost: Button = itemView.cost
        val location: TextView = itemView.location
        val date: TextView = itemView.date
        val time: TextView = itemView.time
        val venue: TextView = itemView.venue
        val image: RelativeLayout = itemView.image

        init {
            image.setOnClickListener{
                callback(absoluteAdapterPosition)
            }
        }





    }


    fun getData(int: Int): UpcomingEventsData {
        return getItem(int)
    }

    class PojoDiffCallback : DiffUtil.ItemCallback<UpcomingEventsData>() {
        override fun areItemsTheSame(oldItem: UpcomingEventsData, newItem: UpcomingEventsData): Boolean {
            return oldItem.location == newItem.location
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem:UpcomingEventsData,
            newItem:UpcomingEventsData
        ): Boolean {
            return oldItem == newItem
        }
    }



}