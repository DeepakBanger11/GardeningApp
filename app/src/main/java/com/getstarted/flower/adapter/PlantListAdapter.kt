package com.getstarted.flower.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getstarted.flower.PlantDetail
import com.getstarted.flower.R
import com.getstarted.flower.data.Plant
import com.google.gson.Gson

class PlantListAdapter(
    private val context: Context,
    private val plantList: List<Plant>) :
    RecyclerView.Adapter<PlantListAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.plant_list, parent, false)
        return PlantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plantList[position]
        holder.bind(plant)
        holder.itemView.findViewById<CardView>(R.id.plantCardView).setOnClickListener(){
            val intent = Intent(context, PlantDetail::class.java)
            val gson = Gson()
            val plantJsonString = gson.toJson(plant)
            intent.putExtra("plantJson", plantJsonString)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.plantNameTextView)
        private val imageHolder:ImageView = itemView.findViewById(R.id.plantImageView)
        fun bind(plant: Plant) {
            nameTextView.text = plant.name
            Glide.with(itemView)
                .load(plant.imageUrl)
                .into(imageHolder)
            // Bind other data to your UI elements
        }



    }
}