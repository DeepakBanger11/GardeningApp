package com.getstarted.flower


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.ViewPager
import com.getstarted.flower.adapter.TabPagerAdapter
import com.getstarted.flower.data.Plant
import com.getstarted.flower.fragment.MyGardenFragment
import com.getstarted.flower.fragment.MyPlantsFragment
import com.getstarted.flower.model.PlantViewModel
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val plantViewModel:PlantViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("getall","${plantViewModel.getAllPlant}")

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        val tabAdapter = TabPagerAdapter(supportFragmentManager)

        tabAdapter.addFragment(MyGardenFragment(), "My Garden")
        tabAdapter.addFragment(MyPlantsFragment(), "My Plants")
        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)

        // Set custom view for each tab
        val tab1 = tabLayout.getTabAt(0)
        val tab2 = tabLayout.getTabAt(1)
        tab1?.customView = createTabView("My Garden", R.drawable.gardening)
        tab2?.customView = createTabView("My Plants", R.drawable.plant)


    }
    private fun createTabView(tabTitle: String, iconResId: Int): View {
        val tabView = layoutInflater.inflate(R.layout.tab_layout, null) as LinearLayout
        val tabText = tabView.findViewById<TextView>(R.id.tab_text)
        val tabIcon = tabView.findViewById<ImageView>(R.id.tab_icon)
        tabText.text = tabTitle
        tabIcon.setImageResource(iconResId)
        return tabView
    }
}