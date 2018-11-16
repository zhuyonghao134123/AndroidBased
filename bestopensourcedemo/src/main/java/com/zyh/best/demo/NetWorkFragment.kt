package com.zyh.best.demo


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zyh.best.demo.network.OkHttpDemoActivity
import com.zyh.best.demo.network.RetrofitDemoActivity
import com.zyh.best.demo.network.RxAndroidDemoActivity
import kotlinx.android.synthetic.main.fragment_net_work.*


/**
 * 网络
 */
class NetWorkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_net_work, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //OkHttp
        buttonOkHttp.setOnClickListener {
            startActivity(Intent(activity,OkHttpDemoActivity::class.java))
        }

        //Retrofit
        buttonRetrofit.setOnClickListener {
            startActivity(Intent(activity, RetrofitDemoActivity::class.java))
        }

        //RxAndroidSample
        buttonRxAndroid.setOnClickListener {
            startActivity(Intent(activity,RxAndroidDemoActivity::class.java))
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = NetWorkFragment()
    }
}
