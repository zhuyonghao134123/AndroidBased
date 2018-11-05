package com.zyh.activity.fragment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zyh.activity.R
import kotlinx.android.synthetic.main.activity_fragment_basic.*

/**
 * Fragment官方Demo
 */
class FragmentBasicActivity : AppCompatActivity(), HeadlinesFragment.OnHeadlineSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_basic)

        if (fragment_container != null) {
            if (savedInstanceState != null) {
                return
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, HeadlinesFragment.newInstance())
                .commit()
        }
    }

    override fun onArticleSelected(position: Int) {
        // Create fragment and give it an argument for the selected article
        val articleFragment = ArticleFragment.newInstance(position)
        val beginTransaction = supportFragmentManager.beginTransaction()

            beginTransaction.addToBackStack(null)
            beginTransaction.replace(R.id.fragment_container, articleFragment)
            .commit()
    }
}
