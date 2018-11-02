package com.zyh.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.zyh.activity.fragment.FragmentActivity
import com.zyh.activity.fragment.FragmentBasicActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Activity
 */
class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        setContentView(R.layout.activity_main)

        //Activity生命周期
        buttonActivityLifecycle.setOnClickListener {
            startActivity(Intent(this, LifecycleActivity::class.java))
        }

        //Fragment生命周期
        buttonFragmentLifecycle.setOnClickListener {
            startActivity(Intent(this, FragmentActivity::class.java))
        }

        //FragmentBasic官方Demo
        buttonBasicFragment.setOnClickListener {
            startActivity(Intent(this, FragmentBasicActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }
}
