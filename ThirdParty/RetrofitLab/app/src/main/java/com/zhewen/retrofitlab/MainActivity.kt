package com.zhewen.retrofitlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.zhewen.retrofitlab.retrofit.RetrofitHomeActivity
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_first).setOnClickListener {
            startActivity(Intent(this,RetrofitHomeActivity::class.java))
        }
    }
}