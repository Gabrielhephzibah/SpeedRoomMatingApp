package com.cherish.speedroommatingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cherish.speedroommatingapp.R
import com.cherish.speedroommatingapp.ViewModelProviderFactory
import com.cherish.speedroommatingapp.utils.ConnectionStatus
import com.cherish.speedroommatingapp.utils.Status
import com.cherish.speedroommatingapp.viewmodel.UpcomingEventsViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @set:Inject
    var factory: ViewModelProviderFactory? = null
    var pagerAdapter : FragmentStateAdapter? = null
    var upcomingViewModel : UpcomingEventsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        upcomingViewModel = ViewModelProvider(this, factory!!).get(UpcomingEventsViewModel::class.java)
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.upcoming)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.archived)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.options)))
        pagerAdapter = FragmentStateAdapter(this, tabLayout.tabCount)
        viewPager.adapter = pagerAdapter
        viewPager.isUserInputEnabled = false

        makeApiRequest()

        upcomingViewModel!!.getUpcomingData().observe(this, Observer {
            when(it.status){
                Status.EMPTY -> {
                    stateLayout.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    stateLayout.visibility = View.VISIBLE
                    image.setImageResource(R.drawable.ic_error)
                    titleText.text = getString(R.string.errorText)
                    message.text = getString(R.string.errorMessage)
                }
                Status.IDEAL -> {
                    stateLayout.visibility = View.GONE
                }

                else -> {}
            }
        })

        retry.setOnClickListener{
            stateLayout.visibility = View.GONE
            makeApiRequest()

        }



    }

    fun makeApiRequest(){
        if (ConnectionStatus.isOnline(this)){
            upcomingViewModel!!.getUpcomingEvent()
        }else{
            stateLayout.visibility = View.VISIBLE
            image.setImageResource(R.drawable.ic_offline)
            titleText.text = getString(R.string.offline)
            message.text = getString(R.string.offline_message)
        }

    }
}
