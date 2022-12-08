package com.bam.retrofitapiexample.model

import android.content.Context
import androidx.room.Room
import com.bam.retrofitapiexample.api.PostResponse
import com.bam.retrofitapiexample.api.PostRestApi
import com.bam.retrofitapiexample.room.LocalDb
import com.bam.retrofitapiexample.room.PostEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(context: Context) {

    var list = mutableListOf<PostData>()

    private val postService = PostRestApi.getService()
    private val postDao =
        Room.databaseBuilder(context, LocalDb::class.java, "post").build().postDao()


    suspend fun initPosts() {
        withContext(Dispatchers.IO) {
            val result = postService.getAll().execute().body()
            if (result != null) {
                list = convertFromPostResponse(result)
                result.forEach {
                    postDao.insert(PostEntity(it.id, it.userId, it.title, it.body))
                }
            }
        }
    }

    private fun convertFromPostResponse(list: List<PostResponse>?): MutableList<PostData> {
        val result = mutableListOf<PostData>()

        list?.forEach {
            val post = PostData(it.id, it.userId, it.title, it.body)
            result.add(post)
        }

        return result
    }

}