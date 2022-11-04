package com.example.serviceapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var myService: MyService
    private var isMyServiceBound: Boolean = false
    private val myServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            myService = (binder as MyService.MyBinder).service
            myService.setActivityCallback(object : MyCallback {
                override fun sendSomething(value: String) {
                    Toast.makeText(applicationContext, "'${value}' was sended from service", Toast.LENGTH_LONG).show()
                }
            })
            isMyServiceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isMyServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartService = findViewById<Button>(R.id.btn_start_service)
        btnStartService.setOnClickListener {
            startService(Intent(this, MyService::class.java))
            bindService(
                Intent(this, MyService::class.java),
                myServiceConnection,
                Context.BIND_AUTO_CREATE
            )
        }

        val btnSendFromService = findViewById<Button>(R.id.btn_send_from_service)
        btnSendFromService.setOnClickListener {
            myService.sendToActivity("This is data sended from service")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isMyServiceBound) {
            unbindService(myServiceConnection)
        }
    }

    interface MyCallback {
        fun sendSomething(value: String)
    }
}