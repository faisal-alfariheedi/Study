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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KRVAdapter(private val rv: List<KotNote>, val cont: Context): RecyclerView.Adapter<KRVAdapter.ItemViewHolder>()  {
//    lateinit var dbh :Dbhelper
    val dbk= NoteDB.getInstance(cont).KotNoteDao()
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KRVAdapter.ItemViewHolder {
        return KRVAdapter.ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: KRVAdapter.ItemViewHolder, position: Int) {
//        dbh= Dbhelper(cont)
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

                if(cont is kotlinstudy) {
                    alert(rv[position])
                    cont.rvupdate()
                }

            }
            del.setOnClickListener{

                if(cont is kotlinstudy) {
//                    dbh.deleteNote("kot",rv[position])
                    CoroutineScope(Dispatchers.IO).launch {
                        dbk.deleteNote(rv[position])
                    }
                    cont.rvupdate()

                }
            }

        }
    }
    fun alert(note: KotNote) {
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

            if(cont is kotlinstudy) {
                CoroutineScope(Dispatchers.IO).launch {
                    dbk.addeditNote(n)
                }
//                dbh.updateNote("kot",n)
                cont.rvupdate()
            }
        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }
        d.setTitle("Edit note")
        d.setCancelable(false)
        val alert = d.create()
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

class ARVAdapter(private val rv: List<AndNote>, val cont: Context): RecyclerView.Adapter<ARVAdapter.ItemViewHolder>()  {
    //    lateinit var dbh :Dbhelper
    val dba= NoteDB.getInstance(cont).AndNoteDao()
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ARVAdapter.ItemViewHolder {
        return ARVAdapter.ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: ARVAdapter.ItemViewHolder, position: Int) {
//        dbh= Dbhelper(cont)
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
                if(cont is androidstudy) {
                    alert(rv[position])
                    cont.rvupdate()
                }

            }
            del.setOnClickListener{

                if(cont is androidstudy) {
//                    dbh.deleteNote("andr",rv[position])
                    CoroutineScope(Dispatchers.IO).launch {
                        dba.deleteNote(rv[position])
                    }
                    cont.rvupdate()
                }

            }

        }
    }

    fun alert(note: AndNote) {
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

            if(cont is androidstudy) {
                CoroutineScope(Dispatchers.IO).launch {
                    dba.addeditNote(n as AndNote)
                }
//                dbh.updateNote("andr",n)
                cont.rvupdate()
            }

        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }
        d.setTitle("Edit note")
        d.setCancelable(false)
        val alert = d.create()
        if(cont is androidstudy)
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