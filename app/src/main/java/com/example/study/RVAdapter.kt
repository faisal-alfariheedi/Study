package com.example.study

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val rv: ArrayList<Note>, val cont: Context): RecyclerView.Adapter<RVAdapter.ItemViewHolder>()  {
    lateinit var dbh :Dbhelper
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ItemViewHolder {
        return RVAdapter.ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: RVAdapter.ItemViewHolder, position: Int) {
        dbh= Dbhelper(cont)
        val rvv = rv[position].tit
        val rvvd= rv[position].desc
        holder.itemView.apply {
            var ed: ImageButton=findViewById<ImageButton>(R.id.edbut)
            var del: ImageButton=findViewById<ImageButton>(R.id.delbut)
            var rvlisting= findViewById<CardView>(R.id.rvlisting)
            var ct= findViewById<TextView>(R.id.cardtitle)
            var cd= findViewById<TextView>(R.id.carddesc)
            ct.text = rvv.toString()
            cd.text = rvvd.toString()
            rvlisting.setOnClickListener {
                alert(rv[position].full,rvv,cont)

            }
            ed.setOnClickListener{
                alert(rv[position])
            }
            del.setOnClickListener{
                dbh.deleteNote(rv[position])
                if(cont is androidstudy)
                    cont.rvupdate()
                if(cont is kotlinstudy)
                    cont.rvupdate()
            }

        }
    }
    fun alert(note: Note) {
        var n=note
        var d= AlertDialog.Builder(cont)
        lateinit var input: EditText
        lateinit var desc: EditText
        lateinit var full: EditText
        lateinit var vv: View
        d.setPositiveButton("Change") { _, _ ->
            n.tit = input.text.toString()
            n.desc= desc.text.toString()
            n.full= full.text.toString()
            dbh.updateNote(n)
            if(cont is androidstudy)
                cont.rvupdate()
            if(cont is kotlinstudy)
                cont.rvupdate()
        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }
        d.setTitle("Edit note")
        d.setCancelable(false)
        val alert = d.create()
        if(cont is androidstudy)
        vv=cont.layoutInflater.inflate(R.layout.alert,null)
        if(cont is kotlinstudy)
            vv=cont.layoutInflater.inflate(R.layout.alert,null)
        alert.setView(vv)
        input= vv.findViewById(R.id.edn)
        desc=vv.findViewById(R.id.edatb1)
        full=vv.findViewById(R.id.edatb2)
        input.setText(n.tit)
        desc.setText(n.desc)
        full.setText(n.full)
        alert.show()
    }


    override fun getItemCount() = rv.size
}