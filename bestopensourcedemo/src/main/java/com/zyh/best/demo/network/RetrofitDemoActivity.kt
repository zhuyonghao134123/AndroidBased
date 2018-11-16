package com.zyh.best.demo.network

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.zyh.best.demo.R
import kotlinx.android.synthetic.main.activity_retrofit_demo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Retrofit官方Demo
 */
class RetrofitDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_demo)

        //Get同步请求
        buttonGetSynchronous.setOnClickListener {
            // Create a very simple REST adapter which points the GitHub API. 创建一个非常简单的REST适配器，它指向GitHub API。
            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Create an instance of our GitHub API interface. 创建GitHub API接口的实例。
            val gitHub = retrofit.create(GitHub::class.java)

            // Create a call instance for looking up Retrofit contributors. 创建一个调用实例来查找改进贡献者。
            val call = gitHub.contributors("square", "retrofit")

            // Fetch and print a list of the contributors to the library. 获取并打印对库的贡献者列表。
            Thread(Runnable {
                val contributors = call.execute().body()
                Log.d(TAG,contributors.toString())
                for (contributor in contributors!!) {
                    println(TAG + contributor.login + " (" + contributor.contributions + ")")
                }
            }).start()
        }

        //Get异步请求
        buttonGetAsynchronous.setOnClickListener {
            val retrofit=Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val gitHub = retrofit.create(GitHub::class.java)
            val call = gitHub.contributors("square", "retrofit")

            call.enqueue(object: Callback<List<Contributor>>{
                override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {

                }

                override fun onResponse(call: Call<List<Contributor>>, response: Response<List<Contributor>>) {
                    val contributors = response.body()
                    //onResponse 主线程 可以更新UI
                    Toast.makeText(this@RetrofitDemoActivity,""+contributors,Toast.LENGTH_SHORT).show()
                    for (contributor in contributors!!) {
                        println(contributor.login + " (" + contributor.contributions + ")")
                    }
                }
            })
        }
    }

    interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        fun contributors(
            @Path("owner") owner: String,
            @Path("repo") repo: String
        ): Call<List<Contributor>>
    }

    companion object {
        const val API_URL = "https://api.github.com"
        const val TAG = "RetrofitDemoActivity"

        class Contributor(val login: String, val contributions: Int)
    }
}
