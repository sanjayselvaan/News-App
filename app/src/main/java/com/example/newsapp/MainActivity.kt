package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.newsapp.databinding.ActivityMainBinding

import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tabTitle= arrayListOf("Tab-1","Tab-2")
        binding.ViewPager.adapter=FragmentAdapter(supportFragmentManager,lifecycle)
        TabLayoutMediator(binding.tabLayout,binding.ViewPager){
            tab,position->
            tab.text=tabTitle[position]
        }.attach()
    }

    override fun onBackPressed() {

        if(binding.ViewPager.currentItem==1){
            binding.ViewPager.currentItem = 0
        }
        else{
            super.onBackPressed()
        }
    }

}