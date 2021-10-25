package com.example.study

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class Dbhelper(cont: Context): SQLiteOpenHelper(cont, "notes",null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        if(db != null){
            db.execSQL("create table kotnote(id INTEGER PRIMARY KEY,tit text,descc text,fulll text)")
            db.execSQL("create table andnote(id INTEGER PRIMARY KEY,tit text,descc text,fulll text)")        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun addnote(dbs:String,tit:String,d:String,f:String): Long {
        val db = this.writableDatabase
        var st:Long
        val cv= ContentValues()
        cv.put("tit",tit)
        cv.put("descc",d)
        cv.put("fulll",f)
        when(dbs) {
            "kot" ->{
                 st =db.insert("kotnote", null, cv)
            }
            "andr" ->{
                 st= db.insert("andnote",null,cv)
            }
            else -> { st=-1}
        }

        db.close()
        return st
    }

    @SuppressLint("Range")
    fun getall(dbs: String):ArrayList<Note>{
        val db = this.readableDatabase
        var list=arrayListOf<Note>()
        var query=""
        when(dbs){
            "kot"->{
                query="SELECT * from kotnote"
            }
            "andr"->{
                query="SELECT * from andnote"
            }
        }

        var cursor : Cursor? = null
        try {
            cursor=db.rawQuery(query,null)
        }catch (e:Exception){
            db.execSQL(query)
            return ArrayList()
        }
        if(cursor.count > 0) {
            if (cursor.moveToFirst()) {
                do {
                    list.add(
                        Note(
                            cursor.getInt(cursor.getColumnIndex("id")).toString(),
                            cursor.getString(cursor.getColumnIndex("tit")),
                            cursor.getString(cursor.getColumnIndex("descc")),
                            cursor.getString(cursor.getColumnIndex("fulll"))
                        )
                    )
                } while (cursor.moveToNext())
            }
        }
        db.close()
        return list
    }
    fun updateNote(dbs: String,note: Note): Int {
        val db = this.writableDatabase
        var success=0
        var cv = ContentValues()
        cv.put("tit",note.tit)
        cv.put("descc",note.desc)
        cv.put("fulll",note.full)
        when(dbs){
            "kot"->{
                success= db.update("kotnote", cv, "id = ${note.id}", null)
            }
            "andr"->{
                success= db.update("andnote", cv, "id = ${note.id}", null)
            }
        }

        db.close()
        return success
    }

    fun deleteNote(dbs: String,note: Note): Int{
        val db = this.writableDatabase
        var success=0
        when(dbs){
            "kot"->{
                success= db.delete("kotnote", "id = ${note.id}", null)
            }
            "andr"->{
                success= db.delete("andnote", "id = ${note.id}", null)
            }
        }
        db.close()
        return success
//        success > 0 means it worked
    }
}