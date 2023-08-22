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
        changeActionBarTitle(0)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {

            if ((supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name) == CompleteFragment.fragmentCompleteKey) {
                supportFragmentManager.popBackStack()


            } else if ((supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name) == DraftFragment.fragmentDraftKey) {
                supportFragmentManager.popBackStack()

            }
        } else {
            super.onBackPressed()
        }
    }
    fun changeActionBarTitle(stringID:Int){
        when(stringID){
            0->{
                supportActionBar?.title=getString(R.string.app_name)
            }
            1->{
                supportActionBar?.title=getString(R.string.draft)
            }
            2->{
                supportActionBar?.title=getString(R.string.completed)
            }
            else->{
                supportActionBar?.title=getString(R.string.app_name)
            }
        }
    }

}