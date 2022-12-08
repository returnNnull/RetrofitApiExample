package com.bam.retrofitapiexample.model

data class PostData(
    var id: Int,
    var userId: Int,
    var title: String,
    var body: String)