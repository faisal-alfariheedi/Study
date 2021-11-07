package com.example.study.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface KotNoteDao {

    @Query("SELECT * from KotNote")
    fun getall(): LiveData<List<KotNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addeditNote(kotNote: KotNote)

    @Delete
    fun deleteNote(kotNote: KotNote)
}

@Dao
interface AndNoteDao {
    @Query("SELECT * from AndNote")
    fun getall():LiveData<List<AndNote>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun addeditNote(andNote: AndNote)

    @Delete
    fun deleteNote(andNote: AndNote)

}