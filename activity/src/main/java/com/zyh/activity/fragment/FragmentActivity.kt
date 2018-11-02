package com.zyh.activity.fragment

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.zyh.activity.R
import kotlinx.android.synthetic.main.activity_fragment.*

/**
 * Fragment
 */
class FragmentActivity : AppCompatActivity(),
    FirstFragment.OnFirstFragmentInteractionListener,
    SecondFragment.OnSecondFragmentInteractionListener,
    ThreeFragment.OnThreeFragmentInteractionListener {


    override fun onSecondFragmentInteraction(uri: Uri) {
    }

    override fun onThreeFragmentInteraction(uri: Uri) {
    }

    override fun onFirstFragmentInteraction(uri: Uri) {

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content, FirstFragment.newInstance("first1", "first2")).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content, SecondFragment.newInstance("second1", "second2")).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content, ThreeFragment.newInstance("three1", "three2")).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, FirstFragment.newInstance("first1", "first2")).commit()
        }
    }
}
