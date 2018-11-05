package com.zyh.views.cardview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zyh.views.R

/**
 * Launcher Activity for the CardView sample app.
 */
class CardViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CardViewFragment())
                .commit()
        }
    }
}
