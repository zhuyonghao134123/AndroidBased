package com.zyh.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import com.zyh.views.cardview.CardViewActivity
import com.zyh.views.recyclerview.RecyclerViewActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * User interface & navigation
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RecyclerView
        buttonRecycleView.setOnClickListener {
            startActivity(Intent(this,RecyclerViewActivity::class.java))
        }

        //CardView
        buttonCardView.setOnClickListener {
            startActivity(Intent(this,CardViewActivity::class.java))
        }


    }
}
