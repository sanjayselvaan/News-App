package com.example.newsapp

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container1, BaseFragment())
                .commit()
        } else {
            if(supportFragmentManager.findFragmentByTag(CompleteFragment.fragmentCompleteKey) != null){
                loadFragmentInAppropriateContainer(CompleteFragment.fragmentCompleteKey)
            }
            else if(supportFragmentManager.findFragmentByTag(DraftFragment.fragmentDraftKey) != null){
                loadFragmentInAppropriateContainer(DraftFragment.fragmentDraftKey)
            }
        changeActionBarTitle(0)
    }
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
    fun loadFragmentInAppropriateContainer(fragmentKey:String){
        val fragment = supportFragmentManager.findFragmentByTag(fragmentKey)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val displayFrg = DisplayFragment()
            displayFrg.arguments = fragment?.arguments
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, displayFrg, fragmentKey)
                .addToBackStack(DraftFragment.fragmentDraftKey)
                .commit()
        } else {
            val displayFrg = DisplayFragment()
            displayFrg.arguments = fragment?.arguments
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container2, displayFrg, fragmentKey)
                .addToBackStack(fragmentKey)
                .commit()
        }
    }


    fun changeActionBarTitle(stringID: Int) {
        when (stringID) {
            0 -> {
                supportActionBar?.title = getString(R.string.app_name)
            }

            1 -> {
                supportActionBar?.title = getString(R.string.draft)
            }

            2 -> {
                supportActionBar?.title = getString(R.string.completed)
            }

            else -> {
                supportActionBar?.title = getString(R.string.app_name)
            }
        }
    }

}