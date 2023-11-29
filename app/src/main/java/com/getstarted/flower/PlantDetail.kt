package com.getstarted.flower

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView

import android.widget.TextView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.getstarted.flower.data.Plant
import com.getstarted.flower.data.PlantJson
import com.getstarted.flower.model.PlantViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetail() : AppCompatActivity() {
    private val plantViewModel: PlantViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)
        val plantJsonString = intent.getStringExtra("plantJson")
        var plantName = findViewById<TextView>(R.id.plantDetailName)
        var need = findViewById<TextView>(R.id.plantDetailNeed)
        var duration = findViewById<TextView>(R.id.plantDetailDuration)
        var image = findViewById<ImageView>(R.id.detailImageView)
        var description = findViewById<WebView>(R.id.plantDetailDescription)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        val gson = Gson()
        var plant = gson.fromJson(plantJsonString, PlantJson::class.java)

        if (plant != null) {
            Glide.with(this).load(plant.imageUrl).into(image)
            plantName.text = plant.name
            need.text = plant.wateringInterval+" Watering"
            duration.text = "Cycle "+plant.growZoneNumber
            var htmlContent = plant.description
            description.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)

        }
        backButton.setOnClickListener() {
            finish()
        }
        plantViewModel.getAllPlant.observe(this) { plantList ->
            val isPlantPresent = plantList.any { it.plantId == plant.plantId }
            if (isPlantPresent) {
                floatingActionButton.visibility = View.GONE
            } else {
                floatingActionButton.visibility = View.VISIBLE
            }
        }

        floatingActionButton.setOnClickListener {
            // Check if the plant is already in the database
            plant?.let {
                plantViewModel.addPlant(Plant(0, plant.plantId, plant.name, plant.description, plant.wateringInterval, plant.growZoneNumber, plant.imageUrl))
                }
            val intent = Intent(this@PlantDetail, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
