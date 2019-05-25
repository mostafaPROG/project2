package com.example.project2.activity

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.example.project2.R
import com.example.project2.util.Utilities
import github.nisrulz.lantern.Lantern
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("Registered")
class AppFragment : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    private val utilities =Utilities.getCurrentShamsidate()
    //                flashlight
    private lateinit var lantern: Lantern
    private val REQUEST_CODE = 100
    var flag = false
    lateinit var btnFlashlight: ImageView
    lateinit var btnSos: ImageView
    /*************************/
    //  calendar
    lateinit var timeT: TextView
    lateinit var dateT:TextView

    @SuppressLint("SimpleDateFormat", "MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tools_layout2)

        toolbar = findViewById(R.id.tools_toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        //    flashLight
        btnFlashlight = findViewById(R.id.btnFlashlightL)
        btnSos = findViewById(R.id.btnSosL)
        lantern = Lantern(this)
            .checkAndRequestSystemPermission()
            .observeLifecycle(this)

        val list = arrayOf(Manifest.permission.CAMERA)
        if (!lantern.initTorch()) {
            ActivityCompat.requestPermissions(this, list, REQUEST_CODE);
        }

        btnFlashlight.setOnClickListener {
            when (flag) {
                true -> {
                    turnOffFlash()
                }
                false -> {
                    turnOnFlash()
                }
            }
        }
        btnSos.setOnClickListener {
            when (flag) {
                true -> {
                    turnOffFlash()
                }
                false -> {
                    for (i in 1..3) {
                        turnOnFlash()
                        Thread.sleep(500)
                        turnOffFlash()
                        Thread.sleep(300)
                    }

                    for (i in 1..3) {
                        turnOnFlash()
                        Thread.sleep(1500)
                        turnOffFlash()
                        Thread.sleep(300)
                    }

                    for (i in 1..3) {
                        turnOnFlash()
                        Thread.sleep(500)
                        turnOffFlash()
                        Thread.sleep(300)
                    }
                    flag = true
                }
            }
        }
        /*************************************************/

        //      calendar
        timeT = findViewById(R.id.timeCardL)
        dateT = findViewById(R.id.dateCardL)
        val tf = SimpleDateFormat("hh:mm")
        val df = SimpleDateFormat("hh:mm")
        timeT.text = tf.format(Calendar.getInstance().time)
        dateT.text = utilities
        /*******************************************************/

    }

    // flashLight
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (!lantern.initTorch()) {
                Toast.makeText(this, "Camera Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun turnOnFlash() {
        btnFlashlight.setImageResource(R.drawable.on_button)
        lantern
            .enableTorchMode(true)
            .pulse(false)
        flag = true
    }

    private fun turnOffFlash() {
        btnFlashlight.setImageResource(R.drawable.off_button)
        lantern
            .enableTorchMode(false)
            .pulse(false)
        flag = false
    }

    override fun onDestroy() {
        super.onDestroy()
        turnOffFlash()
        lantern.cleanup()
    }
    /************************/

}