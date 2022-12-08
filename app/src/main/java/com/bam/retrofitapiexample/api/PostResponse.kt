package com.bam.retrofitapiexample.api

import androidx.room.Entity
import androidx.room.PrimaryKey


data class PostResponse(
    var id: Int,
    var userId: Int,
    var title: String,
    var body: String)
