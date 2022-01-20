package com.zhewen.kotlinsample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.coroutineScope

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextView.text = "haha"
    }
    lateinit var mTextView: TextView
    lateinit var editText: EditText
}