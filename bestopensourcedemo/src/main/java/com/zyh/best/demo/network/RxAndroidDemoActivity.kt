package com.zyh.best.demo.network

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.zyh.best.demo.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_android_demo.*

/**
 * RxAndroidSample
 * https://github.com/ReactiveX/RxAndroid
 */
class RxAndroidDemoActivity : AppCompatActivity() {
    //一种一次性容器，可容纳多个其他可弃置物品
    private val disposables = CompositeDisposable()

    companion object {
        private const val TAG = "RxAndroidSamples"

        //一个观察者
        internal fun sampleObservable(): Observable<String> {
            return Observable.defer {
                // Do some long running operation//做一些长时间的运行操作
                SystemClock.sleep(5000)
                Observable.just("one", "two", "three", "four", "five")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_android_demo)

        buttonRunScheduler.setOnClickListener {
            onRunSchedulerExampleButtonClicked()
        }
    }

    private fun onRunSchedulerExampleButtonClicked() {
        disposables.add(
            sampleObservable()
                // Run on a background thread//在后台线程上运行
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread//在主线程上得到通知
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<String>() {
                    override fun onComplete() {
                        Log.d(TAG, "onComplete()")
                    }
                    override fun onNext(string: String) {
                        Log.d(TAG, "onNext($string)")
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "onError()", e)
                    }

                })
        )
    }
}
