package com.example.labseven.worker

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.labseven.RetrofitInstance
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class NewPhotoWorker(
    private val context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("NewPhotoPrefs", Context.MODE_PRIVATE)
    }

    private val lastPhotoId = sharedPreferences.getString("lastPhotoId", null)

    override fun doWork(): Result {
        return try {
            val apiService = RetrofitInstance.api
            Log.w("myApp", "response started")
            val response = runBlocking { apiService.getPhotos() }

            if (response.isSuccessful) {
                Log.w("myApp", "response done")

                val newPhotos = response.body()?.photos?.photo ?: emptyList()

                if (newPhotos.isNotEmpty()) {
                    val newPhotoId = newPhotos[0].id

                    Log.w("myApp", "Last Photo ID: $lastPhotoId, New Photo ID: $newPhotoId")

                    if (lastPhotoId != newPhotoId) {
                        showToast("Found new photos!")

                        sharedPreferences.edit().putString("lastPhotoId", newPhotoId).apply()
                    }
                }

                scheduleNextExecution()

                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun showToast(message: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun scheduleNextExecution() {
        val workRequest = OneTimeWorkRequestBuilder<NewPhotoWorker>()
            .setInitialDelay(30, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}