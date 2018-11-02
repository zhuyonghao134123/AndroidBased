package com.zyh.activity.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.zyh.activity.R

class HeadlinesFragment : ListFragment() {

    private var TAG = "HeadlinesFragment"
    private var listener: OnHeadlineSelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // We need to use a different list item layout for devices older than Honeycomb
            android.R.layout.simple_list_item_activated_1
            android.R.layout.simple_list_item_1
        // Create an array adapter for the list view, using the Ipsum headlines array
        listAdapter=ArrayAdapter(activity!!,android.R.layout.simple_list_item_activated_1,Ipsum.Headlines)
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        listener?.onArticleSelected(position)
        // Set the item as checked to be highlighted when in two-pane layout将选中的项设置为在双窗格布局中突出显示
        listView.setItemChecked(position,true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHeadlineSelectedListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnHeadlineSelectedListener")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (activity!!.supportFragmentManager.findFragmentById(R.id.fragment_container)!=null){
            listView.choiceMode=ListView.CHOICE_MODE_SINGLE
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnHeadlineSelectedListener {
        /** Called by HeadlinesFragment when a list item is selected */
        fun onArticleSelected(position: Int)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HeadlinesFragment()
    }
}
