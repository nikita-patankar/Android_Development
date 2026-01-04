package com.example.assigment1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class KnowledgeHubPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledge_hub_page)

        val callSupport = findViewById<Button>(R.id.support)

        callSupport.setOnClickListener {
            val phoneNumber = "+919307875528"   // Indian mobile number
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
    }
}
