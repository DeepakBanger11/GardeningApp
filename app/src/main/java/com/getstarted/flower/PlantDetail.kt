package com.getstarted.flower

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView

import android.widget.TextView
import com.bumptech.glide.Glide
import com.getstarted.flower.data.Plant
import com.google.gson.Gson

class PlantDetail : AppCompatActivity() {
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
        if (plantJsonString != null) {
            val gson = Gson()
            val plant = gson.fromJson(plantJsonString, Plant::class.java)
            Glide.with(this)
                .load(plant.imageUrl)
                .into(image)
            plantName.text = plant.name
            need.text = "Watering needs"
            duration.text = "every "+(plant.wateringInterval).toString()+" days"
            var htmlContent = plant.description
            description.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)

        }
        backButton.setOnClickListener(){
            finish()
        }
    }

}
