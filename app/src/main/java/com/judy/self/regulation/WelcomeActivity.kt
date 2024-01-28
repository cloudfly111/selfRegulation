package com.judy.self.regulation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.judy.self.regulation.databinding.ActivityWelcomeBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.operators.observable.ObservableInterval
import io.reactivex.rxjava3.internal.operators.observable.ObservableJust
import io.reactivex.rxjava3.internal.operators.observable.ObservableSingleSingle
import io.reactivex.rxjava3.internal.operators.observable.ObservableTimer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer

class WelcomeActivity : AppCompatActivity() {



    private lateinit var binding : ActivityWelcomeBinding

    /** 計時器終止時間 (5 sec)*/
    private val TIMER_LIMIT_NUM = 5L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RxJava 計數器
        val countDownObservable = ObservableInterval(0,2,TimeUnit.SECONDS,Schedulers.io())
        val countObserver = object : Observer<Long> {
            override fun onSubscribe(d: Disposable) {
                Log.i("WelcomeFragment", "onSubscribe")

            }

            override fun onNext(t: Long) {
                Log.i("WelcomeFragment", "onNext: "+t)
                setCountDownUI(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
               finish()
            }

        }
        // observeOn(AndroidSchedulers.mainThread()) : 在mainThread 觀察 countDownObservable
        // takeUntil : 只取到 countDownObservable == TIMER_LIMIT_NUM
        // map : countDownObservable 資料轉換為 TIMER_LIMIT_NUM - countDownObservable
        // safeSubscribe(countObserver)：countObserver 訂閱 最終資料流轉換結果
        countDownObservable.observeOn(AndroidSchedulers.mainThread())
            .takeUntil { sec -> sec == TIMER_LIMIT_NUM }.map { sec -> TIMER_LIMIT_NUM-sec }.safeSubscribe(countObserver)
    }

    private fun setCountDownUI(count:Long){
        binding.CountDownTextview.text = count.toString()
    }

}
