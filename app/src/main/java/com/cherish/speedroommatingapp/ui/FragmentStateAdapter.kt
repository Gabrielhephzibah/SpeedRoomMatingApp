package com.cherish.speedroommatingapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentStateAdapter(fragmentActivity: FragmentActivity, private var tabCount :Int) : FragmentStateAdapter(fragmentActivity) {


    override fun createFragment(position: Int): Fragment {
        return IncomingEventsFragment().newInstance()


    }

    override fun getItemCount(): Int {
        return tabCount
    }
}