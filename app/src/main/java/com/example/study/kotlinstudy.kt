package com.example.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class kotlinstudy : AppCompatActivity() {
    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlinstudy)
        rv = findViewById(R.id.rv)
        var input = ArrayList<ArrayList<String>>()
        rv.layoutManager = LinearLayoutManager(this)
        input.add(arrayListOf("main function","fun main(){\n}"))
        input.add(arrayListOf("printing","print(\"\") or use sout as shortcut"))
        input.add(arrayListOf(""))
        rv.adapter=RVAdapter(input,this)

    }
}