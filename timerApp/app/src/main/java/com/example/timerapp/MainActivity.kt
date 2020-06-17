package com.example.timerapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.gms.ads.AdActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val handler = Handler()
    var timeval = 0
    var Prflg = true

//    lateinit var mAdView :AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
*/
        //handlerの処理
        val runnable = object : Runnable {
            //メッセージの確認
            override fun run() {
                timeval++
                timeTotext(timeval)?.let {
                    tV_time.text = it
                }

                handler.postDelayed(this, 1000)

            }
        }

        bt_start.setOnClickListener { it: View? ->
            if (Prflg == true) {
//                handler.post(runnable)
                handler.postDelayed(runnable, 1000)

                bt_start.text = getString(R.string.stop_val)
                bt_start.setBackgroundColor(Color.rgb(0xFF,0x68,0x00))
                Prflg = false

            } else {
                handler.removeCallbacks(runnable)
                bt_start.setBackgroundColor(Color.rgb(0x6D,0xCE,0xFA))

                bt_start.text = getString(R.string.start_val)
                Prflg = true
            }

//            var calendar = Calendar.getInstance()
//            var ho = calendar.get(Calendar.HOUR)
//            var mi = calendar.get(Calendar.MINUTE)
//            var sc = calendar.get(Calendar.SECOND)

//            tV_time.text = " $mi : $sc"

//            calendar
//            tV_time.text = Calendar.getInstance().time.toString()

//            var calendar = Calendar.getInstance()
//            calendar.timeInMillis = System.currentTimeMillis()
//            calendar.add(Calendar.SECOND,5)


//            tV_time.text = calendar.getMaximum(1)

        }

        bt_reset.setOnClickListener {
            tV_time.text = getString(R.string.time_def)
            timeval = 0
        }
    }

    private fun timeTotext(time: Int = 0): String? {
        return if (time < 0) {
            null                                    // 時刻が0未満の場合 null
        } else if (time == 0) {
            getString(R.string.time_def)            // ０の場合
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d:%3$02d".format(h, m, s)  // 表示に整形
        }
    }
}
