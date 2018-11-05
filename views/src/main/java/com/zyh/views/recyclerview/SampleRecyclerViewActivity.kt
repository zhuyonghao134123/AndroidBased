package com.zyh.views.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ViewAnimator
import com.zyh.views.R
import com.zyh.views.common.logger.Log
import com.zyh.views.common.logger.LogFragment
import com.zyh.views.common.logger.LogWrapper
import com.zyh.views.common.logger.MessageOnlyLogFilter

/**
 * RecyclerView官方Demo
 */
class SampleRecyclerViewActivity : AppCompatActivity() {

    // Whether the Log Fragment is currently shown
    private var logShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_recycler_view)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.sample_content_fragment, RecyclerViewFragment())
                commit()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sample_recyclerview, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu!!.findItem(R.id.menu_toggle_log).run {
            isVisible = findViewById<ViewAnimator>(R.id.sample_output) is ViewAnimator
            setTitle(if (logShown) R.string.sample_hide_log else R.string.sample_show_log)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_toggle_log -> {
            logShown = !logShown

            val output = findViewById<ViewAnimator>(R.id.sample_output)
            output.displayedChild = if (logShown) 1 else 0

            invalidateOptionsMenu()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        initializeLogging()
    }

    /** Create a chain of targets that will receive log data  */
    private fun initializeLogging() {
        // Wraps Android's native log framework.
        val logWrapper = LogWrapper()

        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.logNode = logWrapper

        // Filter strips out everything except the message text.
        val msgFilter = MessageOnlyLogFilter()
        logWrapper.next = msgFilter

        // On screen logging via a fragment with a TextView.
        val logFragment = supportFragmentManager.findFragmentById(R.id.log_fragment) as LogFragment
        msgFilter.next = logFragment.logView

        Log.i(TAG, "Ready")
    }

    companion object {
        const val TAG = "SampleRecyclerViewActivity"
    }
}
