package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(){
    companion object{
        var endTime:Long=0

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.mainContainer,BaseFragment()).commit()
    }

    override fun onBackPressed() {

        if((supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount-1).name)=="fragment_2"){
            supportFragmentManager.popBackStack()
            endTime=System.currentTimeMillis()
        }
        else if((supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount-1).name)=="fragment_1"){
            supportFragmentManager.popBackStack()
        }
        else{
            super.onBackPressed()

        }
    }
}