package com.example.serviceapp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder

class MyService : Service() {

    private lateinit var activityCallback: MainActivity.MyCallback

    private val binder: Binder = MyBinder()

    override fun onBind(p0: Intent?): IBinder = binder

    inner class MyBinder : Binder() {
        val service: MyService
            get() = this@MyService
    }

    fun setActivityCallback(activityCallback: MainActivity.MyCallback){
        this.activityCallback = activityCallback

        object : CountDownTimer(15000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                activityCallback.sendSomething("${(millisUntilFinished / 1000)}")
            }

            override fun onFinish() {
                activityCallback.sendSomething("done!")
            }
        }.start()
    }
}