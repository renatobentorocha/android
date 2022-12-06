package com.example.jetweatherforecast.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetweatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    fun read(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE city = :city")
    suspend fun read(city: String): Favorite

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(favorite: Favorite)

    @Query("DELETE FROM favorite")
    suspend fun delete()

    @Delete
    suspend fun delete(favorite: Favorite)
}