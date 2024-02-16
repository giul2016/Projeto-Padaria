package com.example.pizzaria

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ThaksYouScreem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thaks_you_screem)

        window.statusBarColor=Color.parseColor("#E0E0E0")
    }
}