package com.sample.tabnavigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class PagerAdapter(fm:FragmentManager,NumOfTabs:Int) : FragmentStatePagerAdapter(fm) {
    private var numOfTabs:Int?=null
    init {
        numOfTabs=NumOfTabs
    }
    override fun getItem(position: Int): Fragment =when(position){
     0->TabFragment1()
     1->TabFragment2()
     2->TabFragment3()
        else -> Fragment()
    }

    override fun getCount(): Int {
        return numOfTabs!!
    }
}