package com.zhewen.jetpacklab.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.zhewen.jetpacklab.R
import java.util.concurrent.TimeUnit

class WorkManagerHomeActivity:AppCompatActivity(R.layout.activity_workmanager_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var constraints: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        var workRequest = OneTimeWorkRequestBuilder<TestWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)
//        WorkManager.getInstance(this.applicationContext).beginUniqueWork()
//        val workRequest = OneTimeWorkRequest.from(TestWorker::class.java)
//        val workRequest = PeriodicWorkRequestBuilder<TestWorker>(1,TimeUnit.HOURS,15,TimeUnit.MINUTES).build()
    }
}