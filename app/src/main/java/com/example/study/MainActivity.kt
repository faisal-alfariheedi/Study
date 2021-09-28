package com.example.study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ja=findViewById<Button>(R.id.javat)
        val kot=findViewById<Button>(R.id.kott)

        ja.setOnClickListener {
            switcher(0)
        }
        kot.setOnClickListener{
            switcher(1)
        }


    }

    fun switcher(i:Int){
        val intent: Intent
        if (i ==0){intent=Intent(this,androidstudy::class.java)}
        else{intent=Intent(this,kotlinstudy::class.java)}
        startActivity(intent)

    }
}