package com.example.study.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities=[KotNote::class, AndNote::class],version=1,exportSchema = false)
abstract class NoteDB: RoomDatabase() {

    companion object {
        @Volatile
        var instance: NoteDB?=null
        fun getInstance(cont: Context): NoteDB {
            return instance ?:synchronized(this){
                instance ?: buildDatabase(cont).also{ instance =it}
            }
        }
        fun buildDatabase(cont: Context): NoteDB {
            return Room.databaseBuilder(cont, NoteDB::class.java,"Celeb").build()
        }
    }
    abstract fun KotNoteDao(): KotNoteDao
    abstract fun AndNoteDao(): AndNoteDao

}