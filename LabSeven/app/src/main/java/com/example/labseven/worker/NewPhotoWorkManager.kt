package com.example.labseven.worker

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object NewPhotoWorkManager {
    fun scheduleNewPhotoCheck(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<NewPhotoWorker>().build()

        WorkManager.getInstance(context).enqueue(workRequest)

        WorkManager.getInstance(context).enqueueUniqueWork(
            "NewPhotoCheck",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }
}