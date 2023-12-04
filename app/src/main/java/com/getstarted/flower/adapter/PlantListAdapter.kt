package com.getstarted.flower.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getstarted.flower.PlantDetail
import com.getstarted.flower.R
import com.getstarted.flower.api.model.Data
import com.google.gson.Gson

class PlantListAdapter() :
    PagingDataAdapter<Data, PlantListAdapter.PlantViewHolder>(PlantListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.plant_list, parent, false)
        return PlantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = getItem(position)
        plant?.let { holder.bind(it) }
        holder.itemView.findViewById<CardView>(R.id.plantCardView).setOnClickListener() {
            val intent = Intent(holder.itemView.context, PlantDetail::class.java)
            val gson = Gson()
            val plantJsonString = gson.toJson(plant)
            intent.putExtra("plantJson", plantJsonString)
            holder.itemView.context.startActivity(intent)
        }
    }

    class PlantListDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Data,
            newItem: Data,
        ) = oldItem == newItem

        override fun getChangePayload(oldItem: Data, newItem: Data): Any? {
            if (oldItem != newItem) {
                return newItem
            }
            return super.getChangePayload(oldItem, newItem)
        }
    }


    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.plantNameTextView)
        private val imageHolder: ImageView = itemView.findViewById(R.id.plantImageView)
        fun bind(plant: Data) {
            nameTextView.text = plant.common_name
            Glide.with(itemView)
                .load(plant.default_image?.original_url ?: "no image")
                .into(imageHolder)
            // Bind other data to your UI elements
        }
    }


}