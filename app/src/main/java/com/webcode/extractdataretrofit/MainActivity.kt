package com.webcode.extractdataretrofit

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // creating a variable for recycler view,
    // array list and adapter class.
    private lateinit var courseRV: RecyclerView
    private lateinit var recyclerDataArrayList: ArrayList<RecyclerData>
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourse)
        progressBar = findViewById(R.id.idPBLoading)

        // creating new array list.
        recyclerDataArrayList = ArrayList()

        // calling a method to
        // get all the courses.
        getAllCourses()
    }

    private fun getAllCourses() {
        // on below line we are creating a retrofit
        // builder and passing our base url
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonkeeper.com/b/")
            // on below line we are calling add
            // Converter factory as Gson converter factory.
            .addConverterFactory(GsonConverterFactory.create())
            // at last we are building our retrofit builder.
            .build()
        // below line is to create an instance for our retrofit api class.
        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

        // on below line we are calling a method to get all the courses from API.
        val call = retrofitAPI.getAllCourses()

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(object : Callback<ArrayList<RecyclerData>> {
            override fun onResponse(
                call: Call<ArrayList<RecyclerData>>,
                response: Response<ArrayList<RecyclerData>>
            ) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful) {

                    // on successful we are hiding our progressbar.
                    progressBar.visibility = View.GONE

                    // below line is to add our data from api to our array list.
                    recyclerDataArrayList = response.body()!!

                    // below line we are running a loop to add data to our adapter class.
                    recyclerViewAdapter =
                        RecyclerViewAdapter(recyclerDataArrayList, this@MainActivity)

                    // below line is to set layout manager for our recycler view.
                    val manager = LinearLayoutManager(this@MainActivity)

                    // setting layout manager for our recycler view.
                    courseRV.layoutManager = manager

                    // below line is to set adapter to our recycler view.
                    courseRV.adapter = recyclerViewAdapter

                }
            }

            override fun onFailure(call: Call<ArrayList<RecyclerData>>, t: Throwable) {
                // in the method of on failure we are displaying a
                // toast message for fail to get data.
                Toast.makeText(this@MainActivity, "Fail to get data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}