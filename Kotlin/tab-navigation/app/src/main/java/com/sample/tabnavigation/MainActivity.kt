package com.sample.tabnavigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        tab_layout.addTab(tab_layout.newTab().setText(R.string.tab_label1))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.tab_label2))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.tab_label3))
        // Set the tabs to fill the entire layout.
        tab_layout.tabGravity = TabLayout.GRAVITY_FILL;
        pager.adapter=PagerAdapter(supportFragmentManager,tab_layout.tabCount)
        pager.addOnPageChangeListener(object :TabLayout.TabLayoutOnPageChangeListener(tab_layout){

        })
        tab_layout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.currentItem = tab!!.position
            }

        })
    }
}
