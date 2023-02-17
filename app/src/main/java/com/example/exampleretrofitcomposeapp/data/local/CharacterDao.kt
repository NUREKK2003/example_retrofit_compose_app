package com.example.exampleretrofitcomposeapp.data.local

import androidx.room.*
import com.example.exampleretrofitcomposeapp.data.models.SerialCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(serialCharacter: List<SerialCharacter>)

    @Delete
    suspend fun delete(serialCharacter: List<SerialCharacter>)

    @Update
    suspend fun update(serialCharacter: List<SerialCharacter>)

    @Query("SELECT * FROM characters_table")
    fun getAll(): Flow<List<SerialCharacter>>

    @Query("SELECT * FROM characters_table WHERE id=:id")
    fun getCharacter(id: Int): Flow<SerialCharacter>

    @Query("DELETE FROM characters_table")
    suspend fun dropDatabase()

}