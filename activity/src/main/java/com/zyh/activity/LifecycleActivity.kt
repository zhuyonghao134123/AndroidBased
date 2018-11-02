package com.zyh.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

/**
 * Activity生命周期
 */
class LifecycleActivity : AppCompatActivity() {
    private val tag = "LifecycleActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        Log.d(tag,"onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag,"onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag,"onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag,"onDestroy")
    }
}
