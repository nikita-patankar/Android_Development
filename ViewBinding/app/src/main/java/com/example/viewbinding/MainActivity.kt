package com.example.viewbinding

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            if (binding.checkBox.isChecked){
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            }else{
                binding.checkBox.buttonTintList = ColorStateList.valueOf(Color.RED)
                Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
