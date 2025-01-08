package com.example.recipesapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,

    @ColumnInfo("title")
    val title:String,

    @ColumnInfo("des")
    val des:String,

    val favid:String,
)