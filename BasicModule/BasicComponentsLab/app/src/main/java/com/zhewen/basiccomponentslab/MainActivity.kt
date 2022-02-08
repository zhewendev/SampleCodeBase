package com.zhewen.basiccomponentslab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.zhewen.basiccomponentslab.activity.ActivityLabActivity
import com.zhewen.basiccomponentslab.broadcast.BroadcastLabActivity
import com.zhewen.basiccomponentslab.contentprovider.ContentProviderLabActivity
import com.zhewen.basiccomponentslab.service.ServiceLabActivity
import com.zhewen.basiccomponentslab.utils.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        findViewById<Button>(R.id.btn_one).setOnClickListener {
            startActivity<ActivityLabActivity>(this@MainActivity) {}
        }
        findViewById<Button>(R.id.btn_two).setOnClickListener {
            startActivity<BroadcastLabActivity>(this@MainActivity) {}
        }
        findViewById<Button>(R.id.btn_three).setOnClickListener {
            startActivity<ServiceLabActivity>(this@MainActivity) {}
        }
        findViewById<Button>(R.id.btn_four).setOnClickListener {
            startActivity<ContentProviderLabActivity>(this@MainActivity) {}
        }
    }
}