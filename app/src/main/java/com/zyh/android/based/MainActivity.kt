package com.zyh.android.based

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Build Your First App
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MESSAGE = "com.zyh.android.based.MESSAGE"
        internal const val PICK_CONTACT_REQUEST = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            sendMessage()
        }
    }

    /** Called when the user taps the Send button */
    private fun sendMessage() {
        val intent = Intent(this, DisplayMessageActivity::class.java)
        val message = editText.text.toString()

        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
    }
}
