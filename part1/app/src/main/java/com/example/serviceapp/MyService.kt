package com.example.serviceapp

import android.app.Service
import android.content.Intent
import android.os.Binder
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
    }

    fun sendToActivity(value: String){
        this.activityCallback.sendSomething(value)
    }
}