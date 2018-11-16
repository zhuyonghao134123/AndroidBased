package com.zyh.views.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zyh.views.R

/**
 * RecyclerView Adapter
 */
class RecyclerViewAdapter(
    private val lists: Array<String>,
    private var mListener: RecyclerViewActivity.ItemClickListenerInterface
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: RecyclerViewActivity.ItemClickListenerInterface = mListener

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_recyclerview_item, viewGroup, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = lists[position]
        viewHolder.textView.text = item

        viewHolder.textView.setOnClickListener {
            mOnClickListener.itemClick(item)
        }
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        var textView: TextView = mView.findViewById(R.id.itemTextView)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    companion object {
        private const val TAG = "RecyclerViewAdapter"
    }
}