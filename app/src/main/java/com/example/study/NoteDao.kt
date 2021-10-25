package com.example.study

import androidx.room.*

@Dao
interface KotNoteDao {

    @Query("SELECT * from KotNote")
    fun getall(): List<KotNote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addeditNote(kotNote: KotNote)

    @Delete
    fun deleteNote(kotNote: KotNote)
}

@Dao
interface AndNoteDao {
    @Query("SELECT * from AndNote")
    fun getall():List<AndNote>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun addeditNote(andNote: AndNote)

    @Delete
    fun deleteNote(andNote: AndNote)

}