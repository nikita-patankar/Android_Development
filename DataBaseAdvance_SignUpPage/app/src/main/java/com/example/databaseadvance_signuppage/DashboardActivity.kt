package com.example.databaseadvance_signuppage

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left + 50,
                systemBars.top + 16,
                systemBars.right + 50,
                systemBars.bottom + 16
            )
            insets
        }

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val emailBtn = findViewById<Button>(R.id.btnEmail)
        val uniqueIdBtn = findViewById<Button>(R.id.btnUniqueId)

        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val uniqueId = intent.getStringExtra("uniqueId")

        tvWelcome.text = "Welcome $username"
        emailBtn.text = "Email: $email"
        uniqueIdBtn.text = "Unique ID: $uniqueId"
    }
}
