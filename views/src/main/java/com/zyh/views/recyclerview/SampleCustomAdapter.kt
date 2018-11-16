package com.zyh.views.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zyh.views.R
import com.zyh.views.common.logger.Log

class SampleCustomAdapter(private val dataSet:Array<String>):RecyclerView.Adapter<SampleCustomAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var textView:TextView
        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener { Log.d(TAG, "Element $adapterPosition clicked.") }
            textView=view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SampleCustomAdapter.ViewHolder {
        // Create a new view.
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.text_row_item,viewGroup,false)
        return ViewHolder(view)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int =dataSet.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d(TAG, "Element $position set.")
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.textView.text=dataSet[position]
    }

    companion object {
        private const val TAG = "SampleCustomAdapter"
    }
}