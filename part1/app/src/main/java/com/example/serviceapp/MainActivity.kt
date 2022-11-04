package com.example.serviceapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var timerService: MyService
    private var isTimerServiceBound: Boolean = false
    private val timerServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            timerService = (binder as MyService.MyBinder).service
            isTimerServiceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isTimerServiceBound = false
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
                timerServiceConnection,
                Context.BIND_AUTO_CREATE
            )
        }

        val btnSendFromService = findViewById<Button>(R.id.btn_send_from_service)
        btnSendFromService.setOnClickListener {

        }
    }
}