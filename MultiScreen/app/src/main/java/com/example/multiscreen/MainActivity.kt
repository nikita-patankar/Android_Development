package com.example.multiscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Companion object to create a constant KEY
    companion object {
        const val KEY = "com.example.multiscreen.MainActivity.KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Getting EditText references
        val order1 = findViewById<EditText>(R.id.order1)
        val order2 = findViewById<EditText>(R.id.order2)
        val order3 = findViewById<EditText>(R.id.order3)
        val order4 = findViewById<EditText>(R.id.order4)

        // Button reference
        val submit = findViewById<Button>(R.id.submit)

        submit.setOnClickListener {

            // Combine all orders into one String
            val ordersPlaced =
                order1.text.toString() + " " +
                        order2.text.toString() + " " +
                        order3.text.toString() + " " +
                        order4.text.toString()

            // Intent to move to OrderData activity
            val intent = Intent(this, OrderData::class.java)

            // Sending data using putExtra
            intent.putExtra(KEY, ordersPlaced)

            // Start next activity
            startActivity(intent)
        }
    }
}
