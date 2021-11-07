package com.example.study.db

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class repo {
    var adb: AndNoteDao
    var kdb: KotNoteDao
    var Alist: LiveData<List<AndNote>>
    var Klist: LiveData<List<KotNote>>
    var con: Context


    constructor(cont: Application) {
        adb = NoteDB.getInstance(cont).AndNoteDao()
        kdb = NoteDB.getInstance(cont).KotNoteDao()
        con = cont
        Alist = adb.getall()
        Klist = kdb.getall()

    }


    fun addedit(note: AndNote){
        andinsnote(adb).execute(note)
    }
    fun addedit(note: KotNote){
        kotinsnote(kdb).execute(note)
    }
    fun delete(note: AndNote){
        anddelnote(adb).execute(note)
    }
    fun delete(note: KotNote){
        kotdelnote(kdb).execute(note)
    }
    fun andgetAll(): LiveData<List<AndNote>> {
        return Alist
    }
    fun kotgetAll(): LiveData<List<KotNote>> {
        return Klist
    }
    class andinsnote(var db: AndNoteDao) : AsyncTask<AndNote, Void, String>(){
        override fun doInBackground(vararg p0: AndNote?): String {
            db.addeditNote(p0[0]!!)
            return ""
        }
    }
    class kotinsnote(var db: KotNoteDao) : AsyncTask<KotNote, Void, String>(){
        override fun doInBackground(vararg p0: KotNote?): String {
            db.addeditNote(p0[0]!!)
            return ""
        }
    }
    class anddelnote(var db: AndNoteDao) : AsyncTask<AndNote, Void, String>(){
        override fun doInBackground(vararg p0: AndNote?): String {
            db.deleteNote(p0[0]!!)
            return ""
        }
    }
    class kotdelnote(var db: KotNoteDao) : AsyncTask<KotNote, Void, String>(){
        override fun doInBackground(vararg p0: KotNote?): String {
            db.deleteNote(p0[0]!!)
            return ""
        }
    }
//    class andinsnote(val db: Any) : AsyncTask<Any, Void, String>(){
//        override fun doInBackground(vararg p0: Any): String {
//            if(db is AndNoteDao)
//                if(p0[0] is AndNote)
//                db.addeditNote(p0[0] as AndNote)
//            else if(db is KotNoteDao)
//                if(p0[0] is KotNote)
//                db.addeditNote(p0[0] as KotNote)
//            return ""
//        }
//    }
}