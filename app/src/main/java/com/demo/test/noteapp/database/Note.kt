package com.demo.test.noteapp.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
class Note(@field:PrimaryKey val id: String, @field:ColumnInfo(name = "note") val note: String)