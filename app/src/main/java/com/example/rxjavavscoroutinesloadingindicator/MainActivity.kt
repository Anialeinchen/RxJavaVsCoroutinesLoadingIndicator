package com.example.rxjavavscoroutinesloadingindicator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.d
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val pictureUrl = "https://raw.githubusercontent.com/WTMBerlin/slides/master/logos/Transparent%20BG%20(black%20text).png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                                .baseUrl("https://jsonplaceholder.typicode.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
        val api = retrofit.create(PlaceholderApiService::class.java)
        api.fetchPlaceholderUsers().enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val index = 0
                val body = response.body()?.get(index)
                val name = body?.name
                val email = body?.email
                d("Retrofun", "Test name: $name")
            }       override fun onFailure(call: Call<List<User>>, t: Throwable) {
                d("Retrofun", "Error")
            }


        })
        btn_glide.setOnClickListener{loadImageWithGlide()}
        //TODO() load JSON with Rx btn_load_rx

        //TODO() load JSON with Rx btn_load_coroutines
    }

    private fun loadImageWithGlide() {
        GlideApp.with(this)
            .load(pictureUrl)
            .into(image)
    }
}
