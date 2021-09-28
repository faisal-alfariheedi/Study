package com.example.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class androidstudy : AppCompatActivity() {
    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_androidstudy)
        rv = findViewById(R.id.rv)
        var input = ArrayList<ArrayList<String>>()
        rv.layoutManager = LinearLayoutManager(this)
        input.add(arrayListOf("main activity","main activity is automatically made by android studio","main activity is automatically made by android studio and looks like this\n" +
                "class androidstudy : AppCompatActivity() {\n" +
                "    override fun onCreate(savedInstanceState: Bundle?) {\n" +
                "        super.onCreate(savedInstanceState)\n" +
                "        setContentView(R.layout.activity_Main)\n" +
                "   }\n" +
                "}"))

        rv.adapter=RVAdapter(input,this)

        }

    }
