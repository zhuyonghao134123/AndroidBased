package com.zyh.views.cardview


import android.os.Build
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar

import com.zyh.views.R

/**
 * Fragment that demonstrates how to use [CardView].
 */
class CardViewFragment : Fragment() {

    private val TAG = "CardViewFragment"

    // The [CardView] widget.
    @VisibleForTesting
    lateinit var cardView: CardView

    // SeekBar that changes the cornerRadius attribute for the cardView widget.
    @VisibleForTesting
    lateinit var radiusSeekBar: SeekBar

    // SeekBar that changes the Elevation attribute for the cardView widget.
    @VisibleForTesting
    lateinit var elevationSeekBar: SeekBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardView = view.findViewById(R.id.cardView)

        radiusSeekBar = view.findViewById(R.id.cardview_radius_seekbar)
        radiusSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d(TAG, "SeekBar Radius progress: $progress")
                cardView.radius = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit // Do nothing

            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit // Do nothing
        })

        elevationSeekBar = view.findViewById(R.id.cardview_elevation_seekbar)
        elevationSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d(TAG, "SeekBar Elevation progress : $progress")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    cardView.elevation = progress.toFloat()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) = Unit // Do nothing

            override fun onStopTrackingTouch(seekBar: SeekBar) = Unit // Do nothing
        })
    }
}
