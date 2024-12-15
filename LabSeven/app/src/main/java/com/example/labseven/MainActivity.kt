package com.example.labseven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.labseven.components.GalleryScreen
import com.example.labseven.worker.NewPhotoWorkManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NewPhotoWorkManager.scheduleNewPhotoCheck(this)

        enableEdgeToEdge()
        setContent {
            GalleryScreen()
        }
    }
}
