package com.example.study

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.db.KotNote
import com.google.android.material.floatingactionbutton.FloatingActionButton


class kotlin : Fragment() {
    lateinit var rv: RecyclerView
    lateinit var fb: FloatingActionButton
    lateinit var bk: Button
    val mvm by lazy { ViewModelProvider(this).get(Vm::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_kotlin, container, false)
        init(v)

        return v
    }




    fun init(v:View) {
        rv =v.findViewById(R.id.rv)
        fb=v.findViewById(R.id.floatingActionButton)
        bk=v.findViewById(R.id.button)
        rv.layoutManager = LinearLayoutManager(requireContext())
        var ad=KRVAdapter(this)
        rv.adapter=ad
        mvm.kotgetAll().observe(viewLifecycleOwner, {
            ad.setNote(it)
        })
        fb.setOnClickListener {
            alert()
        }
        bk.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_kotlin_to_main)
        }
    }
    fun alert() {
        var n= KotNote(0,"","","")
        var d= AlertDialog.Builder(requireContext())
        lateinit var input: EditText
        lateinit var desc: EditText
        lateinit var full: EditText
        d.setPositiveButton("add") { _, _ ->
            n.tit = input.text.toString()
            n.desc= desc.text.toString()
            if(full.text.isEmpty()){n.full=n.desc}else n.full= full.text.toString()
            mvm.addedit(KotNote(0,n.tit,n.desc,n.full))

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