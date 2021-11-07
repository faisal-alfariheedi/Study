package com.example.study

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.study.db.AndNote
import com.example.study.db.KotNote
import com.example.study.db.repo

class Vm (application: Application) : AndroidViewModel(application) {
    var rep= repo(application)//this will select local db or firebase 0 for local 1 for firebase
    private var alist=rep.andgetAll()
    private var klist=rep.kotgetAll()

    fun <T>addedit(note:T){
        if(note is AndNote) {
            rep.addedit(note)
        }else if(note is KotNote){
            rep.addedit(note)
        }
    }

    fun <T>delete(note: T){
        if(note is AndNote) {
            rep.delete(note)
        }else if(note is KotNote){
            rep.delete(note)
        }
    }
    fun andgetAll(): LiveData<List<AndNote>> {
        return alist
    }
    fun kotgetAll(): LiveData<List<KotNote>> {
        return klist
    }


}