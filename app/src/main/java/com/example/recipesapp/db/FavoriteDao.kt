package com.example.recipesapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(favorite: Favorite)


    @Query("SELECT * FROM favorite ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Favorite>>

    @Delete
    suspend fun Delete(favorite: Favorite)

}