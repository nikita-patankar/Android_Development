package com.example.first_project

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonUpload = findViewById<Button>(R.id.btnUpload)
        val buttonDownload = findViewById<Button>(R.id.btnDownload)

        buttonUpload.setOnClickListener {
            Toast.makeText(applicationContext, "Uploading..", Toast.LENGTH_SHORT).show()
        }

        buttonDownload.setOnClickListener {
            Toast.makeText(applicationContext,"Downloading.." , Toast.LENGTH_SHORT).show()
        }
    }
}