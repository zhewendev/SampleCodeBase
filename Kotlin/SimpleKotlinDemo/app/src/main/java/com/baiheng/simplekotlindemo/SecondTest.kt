package com.baiheng.simplekotlindemo

import android.content.DialogInterface
import android.util.Log
import java.time.LocalDate

typealias ST = SecondTest
open class SecondTest: Test<String>() {

    private val TAG: String = this.javaClass.simpleName
    fun handleSuperClass() {
        name = "SecondTest_lalala"
        Log.d(TAG,name)
    }
    private lateinit var listener : DialogInterface.OnClickListener

}