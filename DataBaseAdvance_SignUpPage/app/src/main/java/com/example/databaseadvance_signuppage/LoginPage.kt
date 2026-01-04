package com.example.databaseadvance_signuppage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginPage : AppCompatActivity() {

    companion object {
        const val KEY_UNIQUE_ID = "com.example.databaseadvance_signuppage.KEY_UNIQUE_ID"
    }

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)

        // Handle edge-to-edge padding
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

        val uniqueIdEt = findViewById<EditText>(R.id.etUniqueId)
        val loginBtn = findViewById<Button>(R.id.btnLogin)

        loginBtn.setOnClickListener {
            val id = uniqueIdEt.text.toString().trim()

            if (id.isNotEmpty()) {
                readData(id)
            } else {
                Toast.makeText(this, "Please enter Unique ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(id: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")

        database.child(id).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {

                    val email = snapshot.child("email").value.toString()
                    val username = snapshot.child("username").value.toString()
                    val uniqueId = snapshot.child("uniqueId").value.toString()

                    val intent = Intent(this, DashboardActivity::class.java)
                    intent.putExtra(KEY_UNIQUE_ID, uniqueId)
                    intent.putExtra("email", email)
                    intent.putExtra("username", username)
                    intent.putExtra("uniqueId", uniqueId)

                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
    }
}
