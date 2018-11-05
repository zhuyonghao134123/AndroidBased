package com.zyh.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zyh.views.recyclerview.RecycleViewActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * User interface & navigation
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /**
         * RecycleView
         */
        buttonRecycleView.setOnClickListener {
            startActivity(Intent(this,RecycleViewActivity::class.java))
        }
    }
}
