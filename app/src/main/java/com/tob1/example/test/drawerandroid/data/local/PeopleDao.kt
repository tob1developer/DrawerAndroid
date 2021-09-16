package com.tob1.example.test.drawerandroid.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tob1.example.test.drawerandroid.data.models.People

@Dao
interface PeopleDao {
    @Query("SELECT * FROM people")
    fun getAllPeople(): LiveData<List<People>>

    @Insert
    fun insertAll(vararg people: People)

    @Delete
    fun delete(people: People)

}