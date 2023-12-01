package com.getstarted.flower
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import com.bumptech.glide.Glide
import com.getstarted.flower.api.model.Data
import com.getstarted.flower.data.Plant
import com.getstarted.flower.model.PlantViewModel
import com.getstarted.flower.utils.Constants.DESCRIPTION
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        var plant = gson.fromJson(plantJsonString, Data::class.java)
        var htmlContent = DESCRIPTION
        if (plant != null) {
            Glide.with(this).load(plant.default_image?.original_url?:"no image").into(image)
            plantName.text = plant.common_name
            need.text = plant.watering+" Watering"
            duration.text = "Cycle "+plant.cycle
            Log.d("html",plant.id.toString())
//            plantViewModel.getPlantDetails(plant.id.toString())
//            plantViewModel.speciesDetailsResponse.observe(this) { response ->
//                htmlContent = response.description
                description.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
//            }
        }

        backButton.setOnClickListener() {
            finish()
        }
        plantViewModel.getAllPlant.observe(this) { plantList ->
            val isPlantPresent = plantList.any { it.plantId == plant.scientific_name.toString() }
            if (isPlantPresent) {
                floatingActionButton.visibility = View.GONE
            } else {
                floatingActionButton.visibility = View.VISIBLE
            }
        }

        floatingActionButton.setOnClickListener {
            // Check if the plant is already in the database
            plant?.let {
                plantViewModel.addPlant(Plant(0, plant.scientific_name.toString(), plant.common_name.toString(), DESCRIPTION, plant.watering.toString(), plant.cycle.toString(), plant.default_image.original_url.toString()))
                }
            val intent = Intent(this@PlantDetail, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
