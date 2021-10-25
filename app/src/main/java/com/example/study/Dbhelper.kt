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
            db.execSQL("create table note(id INTEGER PRIMARY KEY,tit text,descc text,fulll text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun addnote(tit:String,d:String,f:String): Long {
        val db = this.writableDatabase
        val cv= ContentValues()
        cv.put("tit",tit)
        cv.put("descc",d)
        cv.put("fulll",f)
        var st= db.insert("note",null,cv)
        db.close()
        return st
    }

    @SuppressLint("Range")
    fun getall():ArrayList<Note>{
        val db = this.readableDatabase
        var list=arrayListOf<Note>()
        val query="SELECT * from note"
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
                            cursor.getString(cursor.getColumnIndex("full"))
                        )
                    )
                } while (cursor.moveToNext())
            }
        }
        db.close()
        return list
    }
    fun updateNote(note: Note): Int {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put("tit",note.tit)
        cv.put("descc",note.desc)
        cv.put("fulll",note.full)
        val success = db.update("note", cv, "id = ${note.id}", null)

        db.close()
        return success
    }

    fun deleteNote(note: Note): Int{
        val db = this.writableDatabase
        val success = db.delete("note", "id = ${note.id}", null)
        db.close()
        return success
//        success > 0 means it worked
    }
}