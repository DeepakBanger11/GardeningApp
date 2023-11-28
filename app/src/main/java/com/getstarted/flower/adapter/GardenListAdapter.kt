package com.getstarted.flower.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getstarted.flower.R
import com.getstarted.flower.data.Plant
import com.getstarted.flower.model.PlantViewModel
import dagger.hilt.android.AndroidEntryPoint

class GardenListAdapter(
    private val context: Context,
    private var gardenList: List<Plant>
) :
    RecyclerView.Adapter<GardenListAdapter.GardenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.garden_list, parent, false)
        return GardenViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GardenViewHolder, position: Int) {
        val garden = gardenList[position]
        holder.bind(garden)
    }

    override fun getItemCount(): Int {
        return gardenList.size
    }

    inner class GardenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.gardenNameTextView)
        private val imageHolder: ImageView = itemView.findViewById(R.id.gardenImageView)

        fun bind(plant: Plant) {
            nameTextView.text = plant.name
            Glide.with(itemView)
                .load(plant.imageUrl)
                .into(imageHolder)
            // Bind other data to your UI elements
        }
    }
    fun updateList(newList: List<Plant>) {
        gardenList = newList
        notifyDataSetChanged()
    }
}