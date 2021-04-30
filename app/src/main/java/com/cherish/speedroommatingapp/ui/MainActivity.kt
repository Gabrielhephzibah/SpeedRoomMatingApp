package com.cherish.speedroommatingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.cherish.speedroommatingapp.R
import com.cherish.speedroommatingapp.ViewModelProviderFactory
import com.cherish.speedroommatingapp.utils.ConnectingStatus
import com.cherish.speedroommatingapp.viewmodel.UpcomingViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @set:Inject
    var factory: ViewModelProviderFactory? = null
    var pagerAdapter : FragmentStateAdapter? = null
    var upcomingViewModel : UpcomingViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        upcomingViewModel = ViewModelProvider(this, factory!!).get(UpcomingViewModel::class.java)
        tabLayout.addTab(tabLayout.newTab().setText("UPCOMING"))
        tabLayout.addTab(tabLayout.newTab().setText("ARCHIVED"))
        tabLayout.addTab(tabLayout.newTab().setText("OPTIONS"))
        pagerAdapter = FragmentStateAdapter(this, tabLayout.tabCount)
        viewPager.adapter = pagerAdapter
        viewPager.isUserInputEnabled = false
        if (ConnectingStatus.isOnline(this)){
            Log.i("here",  "Internet" )
            upcomingViewModel!!.getUpcomingEvent()
        }else{
            Log.i("Here", "No internet")
        }


    }
}
