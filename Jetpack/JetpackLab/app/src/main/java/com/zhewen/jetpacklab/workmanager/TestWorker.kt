package com.zhewen.jetpacklab.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class TestWorker(context:Context,workerParameters: WorkerParameters):Worker(context,workerParameters) {

    override fun doWork(): Result {
        val message = inputData.getString("个人公众号")
        Log.d("TestWorker","doWork $message ${System.currentTimeMillis()}")
        val output: Data = workDataOf("output" to "do work success")
        return Result.success(output)
    }
}