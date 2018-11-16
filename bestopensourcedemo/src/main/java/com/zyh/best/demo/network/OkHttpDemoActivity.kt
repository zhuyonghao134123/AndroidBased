package com.zyh.best.demo.network

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.zyh.best.demo.R
import kotlinx.android.synthetic.main.activity_ok_http_demo.*
import okhttp3.*
import java.io.IOException

/**
 * OkHttp官方Demo
 */
class OkHttpDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http_demo)
        //注意只能有一个OkHttpClient客户端
        val okHttpClient = OkHttpClient()

        //Get同步请求
        buttonGetSynchronous.setOnClickListener {
            Thread(Runnable {
                val request = Request.Builder()
                    .url(url)
                    .build()
                val response = okHttpClient.newCall(request).execute()

                runOnUiThread {
                    textView.text=response.body()!!.string()
                }
            }).start()
        }

        //Get异步请求
        buttonGetAsynchronous.setOnClickListener {
            val request = Request.Builder()
                .url(url)
                .build()
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    //主线程，可以更新UI
                    Toast.makeText(this@OkHttpDemoActivity, e.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call, response: Response) {
                    //工作线程，不能更新UI
                    runOnUiThread {
                        textView.text = response.toString()
                        Toast.makeText(this@OkHttpDemoActivity, response.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        //Post请求
        buttonPostRequest.setOnClickListener {
            val json = bowlingJson("Jesse", "Jake")

            val body = RequestBody.create(JSON, json)
            val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

            okHttpClient.newCall(request).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    textView.text=e.message
                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        textView.text=response.body()!!.string()
                    }
                }
            })
        }

        //接受请求头
        buttonAccessHeaders.setOnClickListener {
            val request = Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build()
            okHttpClient.newCall(request)
                .enqueue(object :Callback{
                    override fun onFailure(call: Call, e: IOException) {
                        Log.e(TAG,e.message)
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call, response: Response) {
                        runOnUiThread {
                            textView.text=response.body()!!.string()+response.headers()

                            System.out.println("Server: " + response.header("Server"))
                            System.out.println("Date: " + response.header("Date"))
                            System.out.println("Vary: " + response.headers("Vary"))
                        }
                    }
                })
        }
    }

    private fun bowlingJson(player1: String, player2: String): String {
        return ("{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}")
    }

    companion object {
        private const val TAG = "OkHttpDemoActivity"
        private const val url = "https://raw.github.com/square/okhttp/master/README.md"
        val JSON = MediaType.get("application/json; charset=utf-8")!!
    }
}


