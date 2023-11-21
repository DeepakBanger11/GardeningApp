package com.getstarted.flower


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.getstarted.flower.tabPagerAdapter.TabPagerAdapter
import com.getstarted.flower.fragment.MyGardenFragment
import com.getstarted.flower.fragment.MyPlantsFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView (R.layout.activity_main)
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val adapter = TabPagerAdapter(supportFragmentManager)
        adapter.addFragment(MyGardenFragment(), "My Garden")
        adapter.addFragment(MyPlantsFragment(), "My Plants")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}