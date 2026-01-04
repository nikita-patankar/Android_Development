package com.example.databaseadvance_signuppage

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Handle edge-to-edge + padding
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

        // Views
        val signUpButton = findViewById<Button>(R.id.btnSignUp)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etUniqueId = findViewById<EditText>(R.id.etUniqueId)
        val Login = findViewById<TextView>(R.id.login)


        signUpButton.setOnClickListener {

            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val uniqueId = etUniqueId.text.toString().trim()

            // Validation
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || uniqueId.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(username, email, password, uniqueId)

            // Firebase reference
            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(uniqueId).setValue(user)
                .addOnSuccessListener {

                    Toast.makeText(
                        this,
                        "User Registered Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Clear fields
                    etUsername.text.clear()
                    etEmail.text.clear()
                    etPassword.text.clear()
                    etUniqueId.text.clear()

                    // Focus first field
                    etUsername.requestFocus()

                    // Hide keyboard
                    val imm =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(etUsername.windowToken, 0)
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Error: ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        Login.setOnClickListener {
            // Navigate to login activity
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)}
    }
}
