package com.example.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class kotlinstudy : AppCompatActivity() {
    lateinit var rv: RecyclerView
    lateinit var dbh:Dbhelper
    lateinit var fb: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlinstudy)
        rv = findViewById(R.id.rv)
        fb=findViewById(R.id.floatingActionButton)
        dbh=Dbhelper(this)
        rv.layoutManager = LinearLayoutManager(this)
        rvupdate()
        fb.setOnClickListener {
            alert()
        }

    }
    fun rvupdate(){
        rv.adapter=RVAdapter(dbh.getall(),this)
    }
    fun alert() {
        var n=Note("0","","","")
        var d= AlertDialog.Builder(this)
        lateinit var input: EditText
        lateinit var desc: EditText
        lateinit var full: EditText
        d.setPositiveButton("add") { _, _ ->
            n.tit = input.text.toString()
            n.desc= desc.text.toString()
            n.full= full.text.toString()
            dbh.updateNote(n)
            rvupdate()
        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }
        d.setTitle("Edit note")
        d.setCancelable(false)
        val alert = d.create()
        var vv: View = layoutInflater.inflate(R.layout.alert,null)
        alert.setView(vv)
        input= vv.findViewById(R.id.edn)
        desc=vv.findViewById(R.id.edatb1)
        full=vv.findViewById(R.id.edatb2)

        alert.show()
    }
}