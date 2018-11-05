package com.zyh.views.recyclerview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.zyh.views.R
import com.zyh.views.recyclerview.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_recyclerview.*

/**
 * RecycleView
 */
class RecycleViewActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        //新建Fragment(list)模版
        buttonFragmentList.setOnClickListener {
            container.visibility= View.VISIBLE

            supportFragmentManager.beginTransaction()
                .add(R.id.container, ItemFragment.newInstance(1))
                .commit()
        }

        //RecyclerView官方Demo
        buttonRecyclerViewDemo.setOnClickListener {
            startActivity(Intent(this,SampleRecyclerViewActivity::class.java))
        }
    }
}
