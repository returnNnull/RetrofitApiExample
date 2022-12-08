package com.bam.retrofitapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bam.retrofitapiexample.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val postService = retrofit.create(PostService::class.java)


        binding.button.setOnClickListener {

            postService.getAll().enqueue(object: Callback<List<PostResponse>>{
                override fun onResponse(
                    call: Call<List<PostResponse>>,
                    response: Response<List<PostResponse>>
                ) {
                    binding.text.text = response.body().toString()
                }

                override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }

            })

        }


    }
}