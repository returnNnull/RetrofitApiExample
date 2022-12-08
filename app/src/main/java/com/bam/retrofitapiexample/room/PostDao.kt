package com.bam.retrofitapiexample.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bam.retrofitapiexample.api.PostResponse

@Dao
interface PostDao {

    @Query("Select * from Post")
    fun getAll(): List<PostEntity>

    @Insert(onConflict = REPLACE)
    fun insert(post: PostEntity)

    @Delete
    fun delete(post: PostEntity)
}