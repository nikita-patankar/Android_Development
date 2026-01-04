package com.example.multiscreen

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_data)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TextView reference
        val customerOrders = findViewById<TextView>(R.id.CustomerOrders)

        // Receiving data from Intent
        val ordersOfCustomer = intent.getStringExtra(MainActivity.KEY)

        // Displaying data safely
        customerOrders.text = ordersOfCustomer ?: "No orders received"
    }
}
