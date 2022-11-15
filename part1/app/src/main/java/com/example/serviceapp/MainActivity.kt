package com.example.serviceapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var myService: MyService
    private var isMyServiceBound: Boolean = false
    private lateinit var textView: TextView

    private val myServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Toast.makeText(
                applicationContext, "Service successfully started and loaded", Toast.LENGTH_SHORT
            ).show()
            myService = (binder as MyService.MyBinder).service
            myService.setActivityCallback(object : MyCallback {
                override fun sendSomething(value: String) {
                    textView.text = value
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

        textView = findViewById(R.id.text)
        val btnStartService = findViewById<Button>(R.id.btn_start_service)
        btnStartService.setOnClickListener {
            startService(Intent(this, MyService::class.java))
            bindService(
                Intent(this, MyService::class.java), myServiceConnection, Context.BIND_AUTO_CREATE
            )
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