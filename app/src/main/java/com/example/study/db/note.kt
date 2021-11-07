package com.example.study.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="kotNote")
class KotNote(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
              @ColumnInfo(name = "tit") var tit:String,
              @ColumnInfo(name = "desc") var desc:String,
              @ColumnInfo(name = "full") var full:String)


@Entity(tableName="AndNote")
class AndNote(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int,
           @ColumnInfo(name = "tit")var tit:String,
           @ColumnInfo(name = "desc")var desc:String,
           @ColumnInfo(name = "full")var full:String)