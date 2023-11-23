package com.getstarted.flower.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.getstarted.flower.R
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.getstarted.flower.model.PlantData

class PlantListAdapter(var plants: ArrayList<PlantData>) :
    RecyclerView.Adapter<PlantListAdapter.PlantViewHolder>() {

    fun updatePlants(newPlants: List<PlantData>) {
        plants.clear()
        plants.addAll(newPlants)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = PlantViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.items_plants, parent, false)
    )

    override fun getItemCount() = plants.size

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(plants[position])
    }

    class PlantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val plantName: TextView = view.findViewById(R.id.plantname)
        fun bind(plantData: PlantData) {
            plantName.text = plantData.plantName
        }
    }
}