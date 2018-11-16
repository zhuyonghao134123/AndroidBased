package com.zyh.views.recyclerview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.zyh.views.R
import kotlinx.android.synthetic.main.activity_recyclerview.*

/**
 * RecycleView
 */
class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var dataLists: Array<String>

    private fun initData() {
        dataLists = arrayOf(
            "Fragment(list)模版",
            "RecyclerView官方Demo"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        initData()

        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)

            adapter = RecyclerViewAdapter(dataLists, ItemClickListener())
        }
    }

    private inner class ItemClickListener : ItemClickListenerInterface {
        override fun itemClick(text: String) {
            when (text) {
                dataLists[0] -> {
                    //新建Fragment(list)模版
                    startActivity(Intent(this@RecyclerViewActivity, FragmentListActivity::class.java))
                }
                dataLists[1] -> {
                    //RecyclerView官方Demo
                    startActivity(Intent(this@RecyclerViewActivity, SampleRecyclerViewActivity::class.java))
                }
            }
        }
    }

    interface ItemClickListenerInterface {
        fun itemClick(text: String)
    }
}
