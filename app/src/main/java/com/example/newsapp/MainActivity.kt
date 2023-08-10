package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.mainContainer, BaseFragment())
                .commit()
        }

    }

    override fun onBackPressed() {

        if ((supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name) == "fragment_2") {
            supportFragmentManager.popBackStack()


        } else if ((supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name) == "fragment_1") {
            supportFragmentManager.popBackStack()

        } else {
            super.onBackPressed()

        }
    }
}