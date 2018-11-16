package com.zyh.views.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zyh.views.R
import com.zyh.views.recyclerview.dummy.DummyContent

/**
 * Fragment
 */
class FragmentListActivity : AppCompatActivity(), FragmentListItem.OnListFragmentInteractionListener{

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_list)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container,FragmentListItem.newInstance(COLUMN_COUNT))
            .commit()
    }

    companion object {
        private const val COLUMN_COUNT = 1
    }
}
