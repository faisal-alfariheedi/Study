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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.study.db.AndNote
import com.example.study.db.KotNote

class KRVAdapter(val cont: Fragment): RecyclerView.Adapter<KRVAdapter.ItemViewHolder>()  {
    private var rv: List<KotNote> = listOf()
    val mvm by lazy { ViewModelProvider(cont).get(Vm::class.java)}

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KRVAdapter.ItemViewHolder {
        return KRVAdapter.ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: KRVAdapter.ItemViewHolder, position: Int) {

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
                alert(rv[position].full,rvv,cont.requireContext())

                ed.setOnClickListener{

                    alert(rv[position])
                }
                del.setOnClickListener{

                    mvm.delete(rv[position])

                }
            }

        }
    }

    fun setNote(n: List<KotNote>){
        rv=n
        notifyDataSetChanged()
    }

    fun alert(note: KotNote) {
        var n=note
        var d= AlertDialog.Builder(cont.requireContext())
        lateinit var input: EditText
        lateinit var desc: EditText
        lateinit var full: EditText
        lateinit var vv: View
        d.setPositiveButton("Change") { _, _ ->
            n.tit = input.text.toString()
            n.desc= desc.text.toString()
            n.full= full.text.toString()
            mvm.addedit(n)

        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }
        d.setTitle("Edit note")
        d.setCancelable(false)
        val alert = d.create()
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

class ARVAdapter(val cont: Fragment): RecyclerView.Adapter<ARVAdapter.ItemViewHolder>()  {
    private var rv: List<AndNote> = listOf()
    val mvm by lazy { ViewModelProvider(cont).get(Vm::class.java)}
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
                alert(rv[position].full,rvv,cont.requireContext())

            }
            ed.setOnClickListener{

                alert(rv[position])
            }
            del.setOnClickListener{

                mvm.delete(rv[position])


            }

        }
    }

    fun setNote(n: List<AndNote>){
        rv=n
        notifyDataSetChanged()
    }

    fun alert(note: AndNote) {
        var n=note
        var d= AlertDialog.Builder(cont.requireContext())
        lateinit var input: EditText
        lateinit var desc: EditText
        lateinit var full: EditText
        lateinit var vv: View
        d.setPositiveButton("Change") { _, _ ->
            n.tit = input.text.toString()
            n.desc= desc.text.toString()
            n.full= full.text.toString()
            mvm.addedit(n)

        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }
        d.setTitle("Edit note")
        d.setCancelable(false)
        val alert = d.create()
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