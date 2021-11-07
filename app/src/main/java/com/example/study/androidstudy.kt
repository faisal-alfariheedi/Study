package com.example.study
//
//import android.os.Bundle
//import android.view.View
//import android.widget.EditText
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.study.db.AndNote
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//
//class androidstudy : AppCompatActivity() {
//    lateinit var rv: RecyclerView
//    lateinit var fb: FloatingActionButton
//    val mvm by lazy { ViewModelProvider(this).get(Vm::class.java) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_androidstudy)
//        init()
//    }
//
//    fun init() {
//        rv = findViewById(R.id.rv)
//        fb = findViewById(R.id.floatingActionButton)
//        rv.layoutManager = LinearLayoutManager(this)
//        var ad = ARVAdapter(this)
//        rv.adapter = ad
//        mvm.andgetAll().observe(viewLifecycleOwner, {
//            ad.setNote(it)
//        })
//        fb.setOnClickListener {
//            alert()
//        }
//    }
//
//    fun alert() {
//        var n = AndNote(0, "", "", "")
//        var d = AlertDialog.Builder(this)
//        lateinit var input: EditText
//        lateinit var desc: EditText
//        lateinit var full: EditText
//        d.setPositiveButton("add") { _, _ ->
//            n.tit = input.text.toString()
//            n.desc = desc.text.toString()
//            if (full.text.isEmpty()) {
//                n.full = n.desc
//            } else n.full = full.text.toString()
//            mvm.addedit(AndNote(0, n.tit, n.desc, n.full))
//
//        }
//            .setNegativeButton("Cancel") { d, _ -> d.cancel() }
//        d.setTitle("Edit note")
//        d.setCancelable(false)
//        val alert = d.create()
//        var vv: View = layoutInflater.inflate(R.layout.alert, null)
//        alert.setView(vv)
//        input = vv.findViewById(R.id.edn)
//        desc = vv.findViewById(R.id.edatb1)
//        full = vv.findViewById(R.id.edatb2)
//
//        alert.show()
//    }
//}